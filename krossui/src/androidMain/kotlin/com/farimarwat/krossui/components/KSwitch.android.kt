package com.farimarwat.krossui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

@Composable
actual fun KSwitch(
    modifier: Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    thumbColor: Color,
    trackColorChecked: Color,
    trackColorUnchecked: Color,
    labelColor: Color,
    labelSize: TextUnit,
    isEnabled: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (label.isNotEmpty()) {
            Text(
                text = label,
                color = if (isEnabled) labelColor else labelColor.copy(alpha = 0.5f),
                fontSize = labelSize,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
        }

        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = isEnabled,
            colors = SwitchDefaults.colors(
                checkedThumbColor = thumbColor,
                uncheckedThumbColor = thumbColor,
                checkedTrackColor = trackColorChecked,
                uncheckedTrackColor = trackColorUnchecked,
                disabledCheckedThumbColor = thumbColor.copy(alpha = 0.5f),
                disabledUncheckedThumbColor = thumbColor.copy(alpha = 0.5f),
                disabledCheckedTrackColor = trackColorChecked.copy(alpha = 0.5f),
                disabledUncheckedTrackColor = trackColorUnchecked.copy(alpha = 0.5f)
            )
        )
    }
}