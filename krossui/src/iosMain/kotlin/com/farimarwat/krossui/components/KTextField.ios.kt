package com.farimarwat.krossui.components

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSRange
import platform.UIKit.*
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun KTextField(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    textColor: Color,
    backgroundColor: Color,
    fontSize: TextUnit,
    isEnabled: Boolean,
    isReadOnly: Boolean,
    maxLines: Int,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    borderColor: Color,
    borderWidth: Float
) {
    UIKitView(
        factory = {
            val textField = UITextField(frame = CGRectMake(0.0, 0.0, 300.0, 44.0))

            // Setup appearance
            textField.borderStyle = UITextBorderStyle.UITextBorderStyleRoundedRect
            textField.layer.borderWidth = borderWidth.toDouble()
            textField.layer.cornerRadius = 8.0

            // Setup keyboard type
            textField.keyboardType = when (keyboardType) {
                KeyboardType.Text -> UIKeyboardTypeDefault
                KeyboardType.Number -> UIKeyboardTypeNumberPad
                KeyboardType.Email -> UIKeyboardTypeEmailAddress
                KeyboardType.Phone -> UIKeyboardTypePhonePad
                KeyboardType.Uri -> UIKeyboardTypeURL
                KeyboardType.Password -> UIKeyboardTypeDefault
                else -> UIKeyboardTypeDefault
            }

            // Setup return key type
            textField.returnKeyType = when (imeAction) {
                ImeAction.Done -> UIReturnKeyType.UIReturnKeyDone
                ImeAction.Go -> UIReturnKeyType.UIReturnKeyGo
                ImeAction.Next -> UIReturnKeyType.UIReturnKeyNext
                ImeAction.Search -> UIReturnKeyType.UIReturnKeySearch
                ImeAction.Send -> UIReturnKeyType.UIReturnKeySend
                else -> UIReturnKeyType.UIReturnKeyDefault
            }

            // Setup text change listener
            val delegate = object : NSObject(), UITextFieldDelegateProtocol {
                override fun textField(
                    textField: UITextField,
                    shouldChangeCharactersInRange: CValue<NSRange>,
                    replacementString: String
                ): Boolean {
                    val currentText = textField.text ?: ""
                    val range = shouldChangeCharactersInRange.useContents {
                        location.toInt() to (location + length).toInt()
                    }
                    val newText = currentText.replaceRange(
                        range.first,
                        range.second,
                        replacementString
                    )
                    onValueChange(newText)
                    return true
                }

                override fun textFieldShouldReturn(textField: UITextField): Boolean {
                    textField.resignFirstResponder()
                    return true
                }
            }

            textField.delegate = delegate
            textField
        },
        update = { view ->
            view.apply {
                // Update text if different
                if (this.text != value) {
                    this.text = value
                }

                // Update placeholder
                this.placeholder = placeholder

                // Update text color
                this.textColor = UIColor(
                    red = textColor.red.toDouble(),
                    green = textColor.green.toDouble(),
                    blue = textColor.blue.toDouble(),
                    alpha = textColor.alpha.toDouble()
                )

                // Update background color
                this.backgroundColor = UIColor(
                    red = backgroundColor.red.toDouble(),
                    green = backgroundColor.green.toDouble(),
                    blue = backgroundColor.blue.toDouble(),
                    alpha = backgroundColor.alpha.toDouble()
                )

                // Update border color
                this.layer.borderColor = UIColor(
                    red = borderColor.red.toDouble(),
                    green = borderColor.green.toDouble(),
                    blue = borderColor.blue.toDouble(),
                    alpha = borderColor.alpha.toDouble()
                ).CGColor

                // Update font size
                if (fontSize != TextUnit.Unspecified) {
                    this.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
                }

                // Update enabled state
                this.enabled = isEnabled
                this.userInteractionEnabled = !isReadOnly

                // Update appearance based on state
                this.alpha = if (isEnabled) 1.0 else 0.6
            }
        },
        modifier = modifier
    )
}
