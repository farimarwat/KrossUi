package com.farimarwat.krossui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.isSpecified
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.takeOrElse

@Composable
public actual fun KOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    colors: KOutlinedButtonColors,
    fontSize: TextUnit,
    cornerRadius: Dp,
    borderWidth: Dp
) {
    val borderColor = if (enabled) colors.borderColor else colors.disabledBorderColor
    val customBorder = borderWidth.isSpecified || borderColor.isSpecified
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = if (cornerRadius.isSpecified) RoundedCornerShape(cornerRadius) else ButtonDefaults.outlinedShape,
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = colors.containerColor,
            contentColor = colors.contentColor,
            disabledContainerColor = colors.disabledContainerColor,
            disabledContentColor = colors.disabledContentColor
        ),
        border = if (customBorder) {
            BorderStroke(
                width = borderWidth.takeOrElse { 1.dp },
                color = borderColor.takeOrElse {
                    if (enabled) {
                        MaterialTheme.colorScheme.outline
                    } else {
                        MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f)
                    }
                }
            )
        } else {
            ButtonDefaults.outlinedButtonBorder(enabled)
        }
    ) {
        Text(text = text, fontSize = fontSize)
    }
}
