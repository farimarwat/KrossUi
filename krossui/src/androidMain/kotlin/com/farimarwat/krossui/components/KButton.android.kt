package com.farimarwat.krossui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.text.TextStyle

@Composable
actual fun KButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    textColor: Color,
    backgroundColor: Color,
    fontSize: TextUnit,
    isEnabled: Boolean
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
            disabledContainerColor = backgroundColor.copy(alpha = 0.3f),
            disabledContentColor = textColor.copy(alpha = 0.3f)
        )
    ) {
        Text(
            text = text,
            style = TextStyle(
                color = textColor,
                fontSize = fontSize
            )
        )
    }
}
