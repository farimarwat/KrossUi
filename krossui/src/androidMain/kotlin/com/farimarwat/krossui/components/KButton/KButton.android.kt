package com.farimarwat.krossui.components.KButton

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
    colors: KButtonColors,
    fontSize: TextUnit,
    isEnabled: Boolean
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) colors.backgroundColor else colors.disabledBackgroundColor,
            contentColor = if (isEnabled) colors.textColor else colors.disabledTextColor
        )
    ) {
        Text(
            text = text,
            fontSize = fontSize
        )
    }
}
