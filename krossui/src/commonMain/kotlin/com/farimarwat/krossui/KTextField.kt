package com.farimarwat.krossui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

/**
 * A single- or multi-line text input that renders the native control on each platform
 * (Material 3 text field on Android, UITextField on iOS).
 *
 * @param value The current text.
 * @param onValueChange Called with the new text when the user edits it.
 * @param modifier The [Modifier] applied to the field.
 * @param enabled Whether the field can be focused and edited.
 * @param readOnly Whether the text can be selected but not edited.
 * @param placeholder Text shown when [value] is empty.
 * @param fontSize The size of the text.
 * @param maxLines The maximum number of visible lines.
 * @param keyboardType The keyboard shown while editing.
 * @param imeAction The action button shown on the keyboard.
 * @param contentPadding Space between the border and the text.
 * @param borderWidth The border width.
 * @param cornerRadius The corner radius.
 * @param colors The field colors. See [KTextFieldDefaults.colors].
 */
@Composable
public expect fun KTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    placeholder: String = "",
    fontSize: TextUnit = TextUnit.Unspecified,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    contentPadding: PaddingValues = KTextFieldDefaults.ContentPadding,
    borderWidth: Dp = KTextFieldDefaults.BorderWidth,
    cornerRadius: Dp = KTextFieldDefaults.CornerRadius,
    colors: KTextFieldColors = KTextFieldDefaults.colors()
)
