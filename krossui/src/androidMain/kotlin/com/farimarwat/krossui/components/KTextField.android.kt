package com.farimarwat.krossui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

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
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder, color = textColor.copy(alpha = 0.6f), fontSize = fontSize
            )
        },
        modifier = Modifier.fillMaxWidth().border(
            width = borderWidth.dp, color = borderColor, shape = RoundedCornerShape(8.dp)
        ),
        enabled = isEnabled,
        readOnly = isReadOnly,
        textStyle = TextStyle(
            color = textColor, fontSize = fontSize
        ),
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType, imeAction = imeAction
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = borderColor,
            unfocusedBorderColor = borderColor.copy(alpha = 0.5f),
            disabledBorderColor = borderColor.copy(alpha = 0.3f),
            cursorColor = textColor,
            focusedTextColor = textColor,
            unfocusedTextColor = textColor,
            disabledTextColor = textColor.copy(alpha = 0.3f),
        ),
        shape = RoundedCornerShape(8.dp)
    )
}