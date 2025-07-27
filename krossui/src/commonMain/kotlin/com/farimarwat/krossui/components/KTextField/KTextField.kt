package com.farimarwat.krossui.components.KTextField

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import com.farimarwat.krossui.components.Common.KPadding

/**
 * A cross-platform text field composable with support for customization such as colors, padding, border, and keyboard options.
 *
 * @param modifier The [Modifier] to be applied to the text field.
 * @param padding The inner padding of the text field.
 * @param value The current text value of the field.
 * @param onValueChange Lambda triggered when the text changes.
 * @param placeholder The placeholder text to display when the field is empty.
 * @param fontSize The font size of the text inside the field.
 * @param isEnabled Whether the text field is enabled for input.
 * @param isReadOnly Whether the text field is read-only (non-editable but selectable).
 * @param maxLines Maximum number of lines the text field can grow to.
 * @param borderWidth Width of the border around the text field.
 * @param cornerRadius Radius of the field's corners.
 * @param keyboardType The type of keyboard to show for input (e.g. Text, Number).
 * @param imeAction The action button to show on the keyboard (e.g. Done, Next).
 * @param colors Visual styling for different states of the text field.
 */
@Composable
expect fun KTextField(
    modifier: Modifier = Modifier,
    padding: KPadding = KPadding.all(12.0),
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    fontSize: TextUnit = TextUnit.Unspecified,
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
    maxLines: Int = 1,
    borderWidth: Int = KTextFieldDefaults.BorderWidth,
    cornerRadius: Double = KTextFieldDefaults.CornerRadius,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    colors: KTextFieldColors = KTextFieldDefaults.colors()
)