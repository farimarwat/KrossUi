package com.farimarwat.krossui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.NativeInteropProperties
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGRect
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSRange
import platform.Foundation.addAttribute
import platform.Foundation.create
import platform.UIKit.*
import platform.darwin.NSObject

/** Text insets in points, resolved from [PaddingValues] for the current layout direction. */
private data class TextInsets(val left: Double, val top: Double, val right: Double, val bottom: Double)

// Custom UITextField with padding support
@OptIn(ExperimentalForeignApi::class)
private class PaddedUITextField(private val padding: TextInsets) : UITextField(frame = CGRectMake(0.0, 0.0, 0.0, 0.0)) {

    override fun textRectForBounds(bounds: CValue<CGRect>): CValue<CGRect> = bounds.useContents {
        CGRectMake(
            origin.x + padding.left,
            origin.y + padding.top,
            size.width - (padding.left + padding.right),
            size.height - (padding.top + padding.bottom)
        )
    }

    override fun editingRectForBounds(bounds: CValue<CGRect>): CValue<CGRect> = textRectForBounds(bounds)

    override fun placeholderRectForBounds(bounds: CValue<CGRect>): CValue<CGRect> = textRectForBounds(bounds)
}

@OptIn(ExperimentalForeignApi::class)
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
    contentPadding: PaddingValues,
    borderWidth: Dp,
    cornerRadius: Dp,
    colors: KTextFieldColors
) {
    val layoutDirection = LocalLayoutDirection.current
    val padding = remember(contentPadding, layoutDirection) {
        TextInsets(
            left = contentPadding.calculateLeftPadding(layoutDirection).value.toDouble(),
            top = contentPadding.calculateTopPadding().value.toDouble(),
            right = contentPadding.calculateRightPadding(layoutDirection).value.toDouble(),
            bottom = contentPadding.calculateBottomPadding().value.toDouble()
        )
    }
    val borderPoints = borderWidth.value.toDouble()

    // Calculate minimum dimensions
    val minSize = remember(placeholder, fontSize, maxLines, padding, borderPoints) {
        val tempTextField = UITextField().apply {
            text = if (placeholder.isNotEmpty()) placeholder else "Sample Text"
            font = UIFont.systemFontOfSize(fontSize.value.toDouble())
            borderStyle = UITextBorderStyle.UITextBorderStyleNone
            sizeToFit()
        }

        tempTextField.intrinsicContentSize.useContents {
            val baseWidth = kotlin.math.max(width.toInt(), 120)
            val baseHeight = if (maxLines > 1) {
                kotlin.math.max(height.toInt() * maxLines, 44)
            } else {
                kotlin.math.max(height.toInt(), 44)
            }

            IntSize(
                width = baseWidth + padding.left.toInt() + padding.right.toInt() + (borderPoints * 2).toInt(),
                height = baseHeight + padding.top.toInt() + padding.bottom.toInt() + (borderPoints * 2).toInt()
            )
        }
    }

    // The delegate is created once, so read the latest values through updated state
    val currentOnValueChange by rememberUpdatedState(onValueChange)
    val currentColors by rememberUpdatedState(colors)
    val currentIsEnabled by rememberUpdatedState(enabled)
    val currentIsReadOnly by rememberUpdatedState(readOnly)

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
                val newText = currentText.replaceRange(range.first, range.second, replacementString)
                currentOnValueChange(newText)
                return true
            }

            override fun textFieldShouldReturn(textField: UITextField): Boolean {
                textField.resignFirstResponder()
                return true
            }

            override fun textFieldDidBeginEditing(textField: UITextField) {
                updateTextFieldColors(
                    textField as PaddedUITextField,
                    currentColors,
                    currentIsEnabled,
                    currentIsReadOnly,
                    isFocused = true
                )
            }

            override fun textFieldDidEndEditing(textField: UITextField) {
                updateTextFieldColors(
                    textField as PaddedUITextField,
                    currentColors,
                    currentIsEnabled,
                    currentIsReadOnly,
                    isFocused = false
                )
            }
        }
    }

    UIKitView(
        properties = NativeInteropProperties,
        modifier = modifier
            .defaultMinSize(minWidth = minSize.width.dp, minHeight = minSize.height.dp),
        factory = {
            val textField = PaddedUITextField(padding = padding)

            textField.borderStyle = UITextBorderStyle.UITextBorderStyleNone
            textField.layer.cornerRadius = cornerRadius.value.toDouble()
            textField.layer.borderWidth = borderPoints

            // Keyboard type
            textField.keyboardType = when (keyboardType) {
                KeyboardType.Text -> UIKeyboardTypeDefault

                KeyboardType.Number -> UIKeyboardTypeNumberPad

                KeyboardType.Email -> UIKeyboardTypeEmailAddress

                KeyboardType.Phone -> UIKeyboardTypePhonePad

                KeyboardType.Uri -> UIKeyboardTypeURL

                KeyboardType.Password -> {
                    textField.secureTextEntry = true
                    UIKeyboardTypeDefault
                }

                else -> UIKeyboardTypeDefault
            }

            // Ime Action
            textField.returnKeyType = when (imeAction) {
                ImeAction.Done -> UIReturnKeyType.UIReturnKeyDone
                ImeAction.Go -> UIReturnKeyType.UIReturnKeyGo
                ImeAction.Next -> UIReturnKeyType.UIReturnKeyNext
                ImeAction.Search -> UIReturnKeyType.UIReturnKeySearch
                ImeAction.Send -> UIReturnKeyType.UIReturnKeySend
                else -> UIReturnKeyType.UIReturnKeyDefault
            }

            textField.delegate = delegate
            updateTextFieldColors(textField, colors, enabled, readOnly, isFocused = false)

            textField
        },
        update = { view ->
            val textField = view

            if (textField.text != value) textField.text = value

            textField.placeholder = placeholder
            updatePlaceholderColor(textField, colors, enabled)

            if (fontSize != TextUnit.Unspecified) {
                textField.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
            }

            val isFocused = textField.isFirstResponder()
            updateTextFieldColors(textField, colors, enabled, readOnly, isFocused)

            textField.enabled = enabled
            textField.userInteractionEnabled = !readOnly
            textField.alpha = if (enabled) 1.0 else 0.6
        }
    )
}

// Helper function to update text field colors based on state
@OptIn(ExperimentalForeignApi::class)
private fun updateTextFieldColors(
    textField: PaddedUITextField,
    colors: KTextFieldColors,
    isEnabled: Boolean,
    isReadOnly: Boolean,
    isFocused: Boolean
) {
    when {
        !isEnabled -> {
            textField.textColor = colors.disabledTextColor.toUiColor()
            textField.backgroundColor = colors.unfocusedBackgroundColor.toUiColor()
            textField.layer.borderColor = colors.disabledBorderColor.toUiColor().CGColor
        }

        isReadOnly -> {
            textField.textColor = colors.readOnlyTextColor.toUiColor()
            textField.backgroundColor = colors.unfocusedBackgroundColor.toUiColor()
            textField.layer.borderColor = colors.unfocusedBorderColor.toUiColor().CGColor
        }

        isFocused -> {
            textField.textColor = colors.textColor.toUiColor()
            textField.backgroundColor = colors.focusedBackgroundColor.toUiColor()
            textField.layer.borderColor = colors.focusedBorderColor.toUiColor().CGColor
        }

        else -> {
            textField.textColor = colors.textColor.toUiColor()
            textField.backgroundColor = colors.unfocusedBackgroundColor.toUiColor()
            textField.layer.borderColor = colors.unfocusedBorderColor.toUiColor().CGColor
        }
    }
}

// Helper function to update placeholder color
@OptIn(ExperimentalForeignApi::class)
private fun updatePlaceholderColor(textField: PaddedUITextField, colors: KTextFieldColors, isEnabled: Boolean) {
    val placeholderText = textField.placeholder ?: return

    val placeholderColor = if (isEnabled) {
        colors.placeholderColor.toUiColor()
    } else {
        colors.disabledTextColor.toUiColor()
    }

    val attributedPlaceholder = platform.Foundation.NSMutableAttributedString.create(placeholderText)
    attributedPlaceholder.addAttribute(
        platform.UIKit.NSForegroundColorAttributeName,
        placeholderColor,
        platform.Foundation.NSMakeRange(0u, placeholderText.length.toULong())
    )
    textField.attributedPlaceholder = attributedPlaceholder
}
