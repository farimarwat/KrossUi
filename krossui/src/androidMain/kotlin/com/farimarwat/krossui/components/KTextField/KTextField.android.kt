package com.farimarwat.krossui.components.KTextField

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions

import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.components.Common.KPadding

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
    val shape = RoundedCornerShape(cornerRadius.dp)

    Box(
        modifier = modifier
            .border(
                width = borderWidth.dp,
                color = colors.unfocusedBorderColor,
                shape = shape
            )
            .background(
                color = Color.Transparent,
                shape = shape
            )
    ) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    color = colors.textColor.copy(alpha = 0.5f),
                    fontSize = fontSize
                )
            },
            enabled = isEnabled,
            readOnly = isReadOnly,
            maxLines = maxLines,
            textStyle = TextStyle(
                color = colors.textColor,
                fontSize = fontSize
            ),
            shape = shape,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = colors.unfocusedBackgroundColor,
                focusedContainerColor = colors.focusedBackgroundColor,
                unfocusedTextColor = colors.textColor,
                cursorColor = colors.textColor,
                focusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                unfocusedIndicatorColor = androidx.compose.ui.graphics.Color.Transparent,
                disabledIndicatorColor = androidx.compose.ui.graphics.Color.Transparent
            ),
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType,
                imeAction = imeAction
            )
        )
    }
}
