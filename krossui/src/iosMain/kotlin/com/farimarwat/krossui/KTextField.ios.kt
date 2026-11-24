package com.farimarwat.krossui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.NativeInteropProperties
import com.farimarwat.krossui.utils.toUiColorOrNull
import kotlin.math.max
import kotlinx.cinterop.CValue
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSAttributedString
import platform.Foundation.NSRange
import platform.Foundation.create
import platform.UIKit.*
import platform.darwin.NSObject

/** Text insets in points, resolved from [PaddingValues] for the current layout direction. */
private data class TextInsets(val left: Double, val top: Double, val right: Double, val bottom: Double)

/** UITextField with optional custom text insets; `null` keeps the native insets. */
private class InsetTextField(var insets: TextInsets?) : UITextField(frame = CGRectMake(0.0, 0.0, 0.0, 0.0)) {

    override fun textRectForBounds(bounds: CValue<CGRect>): CValue<CGRect> {
        val inset = insets ?: return super.textRectForBounds(bounds)
        return bounds.useContents {
            CGRectMake(
                origin.x + inset.left,
                origin.y + inset.top,
                size.width - (inset.left + inset.right),
                size.height - (inset.top + inset.bottom)
            )
        }
    }

    override fun editingRectForBounds(bounds: CValue<CGRect>): CValue<CGRect> =
        if (insets == null) super.editingRectForBounds(bounds) else textRectForBounds(bounds)

    override fun placeholderRectForBounds(bounds: CValue<CGRect>): CValue<CGRect> =
        if (insets == null) super.placeholderRectForBounds(bounds) else textRectForBounds(bounds)
}

/**
 * Native UITextField. With no custom style it is the standard rounded iOS text field (system colors, dark mode);
 * any specified border, shape, padding or color is applied on top.
 */
@Composable
public actual fun KTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    readOnly: Boolean,
    placeholder: String,
    fontSize: TextUnit,
    maxLines: Int,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    contentPadding: PaddingValues?,
    borderWidth: Dp,
    cornerRadius: Dp,
    colors: KTextFieldColors
) {
    val layoutDirection = LocalLayoutDirection.current
    val insets = remember(contentPadding, layoutDirection) {
        contentPadding?.let {
            TextInsets(
                left = it.calculateLeftPadding(layoutDirection).value.toDouble(),
                top = it.calculateTopPadding().value.toDouble(),
                right = it.calculateRightPadding(layoutDirection).value.toDouble(),
                bottom = it.calculateBottomPadding().value.toDouble()
            )
        }
    }
    // A custom border replaces the native rounded style
    val customBorder = borderWidth.isSpecified || cornerRadius.isSpecified ||
        colors.unfocusedBorderColor != Color.Unspecified ||
        colors.focusedBorderColor != Color.Unspecified ||
        colors.disabledBorderColor != Color.Unspecified

    val minSize = remember(placeholder, fontSize, maxLines, insets) {
        val probe = UITextField().apply {
            text = placeholder.ifEmpty { "Sample Text" }
            if (fontSize != TextUnit.Unspecified) font = UIFont.systemFontOfSize(fontSize.value.toDouble())
            borderStyle = UITextBorderStyle.UITextBorderStyleRoundedRect
            sizeToFit()
        }
        probe.intrinsicContentSize.useContents {
            val extraWidth = insets?.let { it.left + it.right } ?: 0.0
            val extraHeight = insets?.let { it.top + it.bottom } ?: 0.0
            max(width, 120.0) + extraWidth to max(height * maxLines + extraHeight, 44.0)
        }
    }

    // The delegate is created once, so read the latest values through updated state
    val currentOnValueChange by rememberUpdatedState(onValueChange)
    val currentStyle by rememberUpdatedState(
        TextFieldStyle(colors, enabled, readOnly, customBorder, borderWidth, cornerRadius)
    )

    val delegate = remember {
        object : NSObject(), UITextFieldDelegateProtocol {
            override fun textField(
                textField: UITextField,
                shouldChangeCharactersInRange: CValue<NSRange>,
                replacementString: String
            ): Boolean {
                val currentText = textField.text ?: ""
                val range = shouldChangeCharactersInRange.useContents {
                    location.toInt() to (location + length).toInt()
                }
                currentOnValueChange(currentText.replaceRange(range.first, range.second, replacementString))
                return true
            }

            override fun textFieldShouldReturn(textField: UITextField): Boolean {
                textField.resignFirstResponder()
                return true
            }

            override fun textFieldDidBeginEditing(textField: UITextField) {
                currentStyle.applyTo(textField, focused = true)
            }

            override fun textFieldDidEndEditing(textField: UITextField) {
                currentStyle.applyTo(textField, focused = false)
            }
        }
    }

    UIKitView(
        properties = NativeInteropProperties,
        modifier = modifier.defaultMinSize(minWidth = minSize.first.dp, minHeight = minSize.second.dp),
        factory = {
            InsetTextField(insets).apply {
                this.keyboardType = when (keyboardType) {
                    KeyboardType.Number -> UIKeyboardTypeNumberPad
                    KeyboardType.Email -> UIKeyboardTypeEmailAddress
                    KeyboardType.Phone -> UIKeyboardTypePhonePad
                    KeyboardType.Uri -> UIKeyboardTypeURL
                    else -> UIKeyboardTypeDefault
                }
                secureTextEntry = keyboardType == KeyboardType.Password
                returnKeyType = when (imeAction) {
                    ImeAction.Done -> UIReturnKeyType.UIReturnKeyDone
                    ImeAction.Go -> UIReturnKeyType.UIReturnKeyGo
                    ImeAction.Next -> UIReturnKeyType.UIReturnKeyNext
                    ImeAction.Search -> UIReturnKeyType.UIReturnKeySearch
                    ImeAction.Send -> UIReturnKeyType.UIReturnKeySend
                    else -> UIReturnKeyType.UIReturnKeyDefault
                }
                this.delegate = delegate
            }
        },
        update = { textField ->
            if (textField.text != value) textField.text = value
            textField.insets = insets
            textField.font = if (fontSize != TextUnit.Unspecified) {
                UIFont.systemFontOfSize(fontSize.value.toDouble())
            } else {
                UIFont.preferredFontForTextStyle(UIFontTextStyleBody)
            }

            val placeholderColor = (if (enabled) colors.placeholderColor else colors.disabledTextColor)
                .toUiColorOrNull()
            if (placeholderColor == null) {
                textField.placeholder = placeholder
            } else {
                textField.attributedPlaceholder = NSAttributedString.create(
                    string = placeholder,
                    attributes = mapOf<Any?, Any?>(NSForegroundColorAttributeName to placeholderColor)
                )
            }

            currentStyle.applyTo(textField, focused = textField.isFirstResponder())
            textField.enabled = enabled
            textField.userInteractionEnabled = !readOnly
            textField.alpha = if (enabled) 1.0 else 0.6
        }
    )
}

/** Everything that decides the field's look; unspecified values keep the system default. */
private data class TextFieldStyle(
    val colors: KTextFieldColors,
    val enabled: Boolean,
    val readOnly: Boolean,
    val customBorder: Boolean,
    val borderWidth: Dp,
    val cornerRadius: Dp
) {
    fun applyTo(textField: UITextField, focused: Boolean) {
        textField.textColor = when {
            !enabled -> colors.disabledTextColor
            readOnly -> colors.readOnlyTextColor
            else -> colors.textColor
        }.toUiColorOrNull()
        textField.backgroundColor =
            (if (focused) colors.focusedBackgroundColor else colors.unfocusedBackgroundColor).toUiColorOrNull()

        if (!customBorder) {
            textField.borderStyle = UITextBorderStyle.UITextBorderStyleRoundedRect
            return
        }
        textField.borderStyle = UITextBorderStyle.UITextBorderStyleNone
        textField.layer.cornerRadius = if (cornerRadius.isSpecified) cornerRadius.value.toDouble() else 6.0
        textField.layer.borderWidth = if (borderWidth.isSpecified) borderWidth.value.toDouble() else 1.0
        val borderColor = when {
            !enabled -> colors.disabledBorderColor.toUiColorOrNull() ?: UIColor.separatorColor
            focused -> colors.focusedBorderColor.toUiColorOrNull() ?: textField.tintColor
            else -> colors.unfocusedBorderColor.toUiColorOrNull() ?: UIColor.separatorColor
        }
        textField.layer.borderColor = borderColor.CGColor
        textField.clipsToBounds = true
    }
}
