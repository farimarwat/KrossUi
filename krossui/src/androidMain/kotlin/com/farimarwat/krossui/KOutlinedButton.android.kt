package com.farimarwat.krossui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

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
    OutlinedButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor,
            contentColor = if (enabled) colors.contentColor else colors.disabledContentColor
        ),
        border = BorderStroke(
            borderWidth,
            if (enabled) colors.borderColor else colors.disabledBorderColor
        )
    ) {
        Text(
            text = text,
            fontSize = fontSize
        )
    }
}
