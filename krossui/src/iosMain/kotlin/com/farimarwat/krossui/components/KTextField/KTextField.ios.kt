package com.farimarwat.krossui.components.KTextField

// iosMain

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.components.Common.KPadding
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

// Custom UITextField with padding support
@OptIn(ExperimentalForeignApi::class)
private class PaddedUITextField(
    private val padding: KPadding
) : UITextField(frame = CGRectMake(0.0, 0.0, 0.0, 0.0)) { // Start with zero frame

    override fun textRectForBounds(bounds: CValue<CGRect>): CValue<CGRect> {
        return bounds.useContents {
            CGRectMake(
                origin.x + padding.leading,
                origin.y + padding.top,
                size.width - (padding.leading + padding.trailing),
                size.height - (padding.top + padding.bottom)
            )
        }
    }

    override fun editingRectForBounds(bounds: CValue<CGRect>): CValue<CGRect> {
        return textRectForBounds(bounds)
    }

    override fun placeholderRectForBounds(bounds: CValue<CGRect>): CValue<CGRect> {
        return textRectForBounds(bounds)
    }
}

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun KTextField(
    modifier: Modifier,
    padding: KPadding,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    fontSize: TextUnit,
    isEnabled: Boolean,
    isReadOnly: Boolean,
    maxLines: Int,
    borderWidth: Int,
    cornerRadius: Double,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    colors: KTextFieldColors,
) {
    // Calculate minimum dimensions
    val minSize = remember(placeholder, fontSize, maxLines, padding) {
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
                width = baseWidth + padding.leading.toInt() + padding.trailing.toInt() + (borderWidth * 2),
                height = baseHeight + padding.top.toInt() + padding.bottom.toInt() + (borderWidth * 2)
            )
        }
    }

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
                onValueChange(newText)
                return true
            }

            override fun textFieldShouldReturn(textField: UITextField): Boolean {
                textField.resignFirstResponder()
                return true
            }

            override fun textFieldDidBeginEditing(textField: UITextField) {
                updateTextFieldColors(textField as PaddedUITextField, colors, isEnabled, isReadOnly, isFocused = true)
            }

            override fun textFieldDidEndEditing(textField: UITextField) {
                updateTextFieldColors(textField as PaddedUITextField, colors, isEnabled, isReadOnly, isFocused = false)
            }
        }
    }

    UIKitView(
        modifier = modifier
            .defaultMinSize(minWidth = minSize.width.dp, minHeight = minSize.height.dp),
        factory = {
            val textField = PaddedUITextField(padding = padding)

            textField.borderStyle = UITextBorderStyle.UITextBorderStyleNone
            textField.layer.cornerRadius = cornerRadius
            textField.layer.borderWidth = borderWidth.toDouble()

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
            updateTextFieldColors(textField, colors, isEnabled, isReadOnly, isFocused = false)

            textField
        },
        update = { view ->
            val textField = view

            if (textField.text != value) textField.text = value

            textField.placeholder = placeholder
            updatePlaceholderColor(textField, colors, isEnabled)

            if (fontSize != TextUnit.Unspecified) {
                textField.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
            }

            val isFocused = textField.isFirstResponder()
            updateTextFieldColors(textField, colors, isEnabled, isReadOnly, isFocused)

            textField.enabled = isEnabled
            textField.userInteractionEnabled = !isReadOnly
            textField.alpha = if (isEnabled) 1.0 else 0.6
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
private fun updatePlaceholderColor(
    textField: PaddedUITextField,
    colors: KTextFieldColors,
    isEnabled: Boolean
) {
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