package com.farimarwat.krossui.components.KTextField

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.ui.Alignment
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

    // Track focus state to apply correct colors
    var isFocused by remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.onFocusChanged { focusState ->
            isFocused = focusState.isFocused
        },
        enabled = isEnabled,
        readOnly = isReadOnly,
        textStyle = TextStyle(
            color = colors.textColor,
            fontSize = fontSize
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = imeAction
        ),
        maxLines = maxLines,
        cursorBrush = SolidColor(colors.textColor),
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 48.dp) // Ensure minimum touch target
                    .background(
                        color = if (isFocused) colors.focusedBackgroundColor else colors.unfocusedBackgroundColor,
                        shape = shape
                    )
                    .border(
                        width = borderWidth.dp,
                        color = if (isFocused) colors.focusedBorderColor else colors.unfocusedBorderColor,
                        shape = shape
                    )
                    .padding(
                        start = padding.leading.dp,
                        end = padding.trailing.dp,
                        top = padding.top.dp,
                        bottom = padding.bottom.dp
                    ),
                contentAlignment = Alignment.CenterStart // Ensure proper alignment
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = colors.textColor.copy(alpha = 0.5f),
                        fontSize = fontSize
                    )
                }
                innerTextField()
            }
        }
    )
}