package com.farimarwat.krossui

import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import com.farimarwat.krossui.utils.MinTouchTarget
import com.farimarwat.krossui.utils.NativeView
import com.farimarwat.krossui.utils.intrinsicDpSize
import com.farimarwat.krossui.utils.scaledSystemFont
import com.farimarwat.krossui.utils.toUiColorOrNull
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
    contentDescription: String?,
    placeholder: String,
    fontSize: TextUnit,
    maxLines: Int,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onImeAction: () -> Unit,
    contentPadding: PaddingValues?,
    borderWidth: Dp,
    cornerRadius: Dp,
    colors: KTextFieldColors
): Unit = PlatformTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    enabled = enabled,
    readOnly = readOnly,
    contentDescription = contentDescription,
    placeholder = placeholder,
    fontSize = fontSize,
    keyboardType = keyboardType,
    imeAction = imeAction,
    onImeAction = onImeAction,
    contentPadding = contentPadding,
    borderWidth = borderWidth,
    cornerRadius = cornerRadius,
    colors = colors,
    secure = keyboardType == KeyboardType.Password
)

/** Shared implementation of [KTextField] and [KSecureField] (UITextField is single-line). */
@Composable
internal fun PlatformTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    readOnly: Boolean,
    contentDescription: String?,
    placeholder: String,
    fontSize: TextUnit,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    onImeAction: () -> Unit,
    contentPadding: PaddingValues?,
    borderWidth: Dp,
    cornerRadius: Dp,
    colors: KTextFieldColors,
    secure: Boolean
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

    // The delegate is created once, so read the latest values through updated state
    val currentOnValueChange by rememberUpdatedState(onValueChange)
    val currentOnImeAction by rememberUpdatedState(onImeAction)
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
                currentOnImeAction()
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

    NativeView(
        factory = {
            InsetTextField(insets).apply {
                // Follow Dynamic Type changes while visible
                adjustsFontForContentSizeCategory = true
                this.keyboardType = when (keyboardType) {
                    KeyboardType.Number -> UIKeyboardTypeNumberPad
                    KeyboardType.Email -> UIKeyboardTypeEmailAddress
                    KeyboardType.Phone -> UIKeyboardTypePhonePad
                    KeyboardType.Uri -> UIKeyboardTypeURL
                    else -> UIKeyboardTypeDefault
                }
                secureTextEntry = secure
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
                scaledSystemFont(fontSize)
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
        },
        modifier = modifier,
        minSize = DpSize(0.dp, MinTouchTarget),
        // VoiceOver reads the placeholder when no label is given
        accessibilityLabel = contentDescription,
        // Default width matches Material's text field (280dp); height is UIKit's plus any custom padding
        preferredSize = { textField ->
            val extraHeight = insets?.let { it.top + it.bottom } ?: 0.0
            DpSize(DefaultTextFieldWidth, textField.intrinsicDpSize().height + extraHeight.dp)
        }
    )
}

/** Default width when the caller does not size the field; same as Material's OutlinedTextField minimum width. */
private val DefaultTextFieldWidth = 280.dp

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
