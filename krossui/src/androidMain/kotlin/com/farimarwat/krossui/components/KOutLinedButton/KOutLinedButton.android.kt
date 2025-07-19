package com.farimarwat.krossui.components.KOutLinedButton

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
actual fun KOutLinedButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    colors: KOutLinedButtonColors,
    fontSize: TextUnit,
    cornerRadius: Double,
    borderWidth: Double,
    isEnabled: Boolean
) {
    OutlinedButton(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = if (isEnabled) colors.backgroundColor else colors.disabledBackgroundColor,
            contentColor = if (isEnabled) colors.textColor else colors.disabledTextColor,
        ),
        border = BorderStroke(
            borderWidth.dp,
            if(isEnabled) colors.borderColor else colors.disabledBorderColor
        )
    ) {
        Text(
            text = text,
            fontSize = fontSize
        )
    }
}