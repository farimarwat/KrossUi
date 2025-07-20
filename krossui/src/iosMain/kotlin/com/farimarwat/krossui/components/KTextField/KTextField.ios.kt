package com.farimarwat.krossui.components.KTextField

// iosMain

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.toUiColor
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
    fontSize: TextUnit,
    isEnabled: Boolean,
    isReadOnly: Boolean,
    maxLines: Int,
    borderWidth: Int,
    cornerRadius:Double,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    colors: KTextFieldColors
) {
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
        }
    }
    UIKitView(
        modifier = modifier,
        factory = {
            val textField = UITextField(frame = CGRectMake(0.0, 0.0, 300.0, 44.0))

            textField.borderStyle = UITextBorderStyle.UITextBorderStyleNone
            textField.layer.cornerRadius = cornerRadius
            textField.layer.borderWidth = borderWidth.toDouble()
            textField.layer.borderColor = colors.borderColor.toUiColor().CGColor
            textField.backgroundColor = colors.backgroundColor.toUiColor()


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
            textField
        },
        update = { view ->
            // Update text
            if (view.text != value) view.text = value

            // Placeholder
            view.placeholder = placeholder

            // Colors
            view.textColor = colors.textColor.toUiColor()
            view.backgroundColor = colors.backgroundColor.toUiColor()
            view.layer.borderColor = colors.borderColor.toUiColor().CGColor

            // Font size
            if (fontSize != TextUnit.Unspecified) {
                view.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
            }

            // Enable / Read-only states
            view.enabled = isEnabled
            view.userInteractionEnabled = !isReadOnly
            view.alpha = if (isEnabled) 1.0 else 0.6
        }
    )
}
