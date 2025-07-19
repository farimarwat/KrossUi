package com.farimarwat.krossui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction

@Composable
expect fun KTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    textColor: Color = Color.Black,
    backgroundColor: Color = Color.White,
    fontSize: TextUnit = TextUnit.Unspecified,
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
    maxLines: Int = 1,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    borderColor: Color = Color.Gray,
    borderWidth: Float = 1f
)