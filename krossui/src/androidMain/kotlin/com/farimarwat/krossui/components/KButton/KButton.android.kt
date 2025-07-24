package com.farimarwat.krossui.components.KButton

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
actual fun KButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    colors: KButtonColors,
    fontSize: TextUnit,
    cornerRadius: Double,
    isEnabled: Boolean
) {
    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) colors.containerColor else colors.disabledContainerColor,
            contentColor = if (isEnabled) colors.contentColor else colors.disabledContentColor
        )
    ) {
        Text(
            text = text,
            fontSize = fontSize
        )
    }
}
