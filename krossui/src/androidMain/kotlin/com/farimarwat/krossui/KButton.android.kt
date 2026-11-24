package com.farimarwat.krossui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.isSpecified

@Composable
public actual fun KButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    colors: KButtonColors,
    fontSize: TextUnit,
    cornerRadius: Dp
) {
    // Unspecified values fall back to the app's MaterialTheme inside Material's own defaults
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = if (cornerRadius.isSpecified) RoundedCornerShape(cornerRadius) else ButtonDefaults.shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.containerColor,
            contentColor = colors.contentColor,
            disabledContainerColor = colors.disabledContainerColor,
            disabledContentColor = colors.disabledContentColor
        )
    ) {
        Text(text = text, fontSize = fontSize)
    }
}
