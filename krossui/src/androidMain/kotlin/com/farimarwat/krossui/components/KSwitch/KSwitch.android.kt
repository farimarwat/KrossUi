package com.farimarwat.krossui.components.KSwitch

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
   colors: KSwitchColors,
    isEnabled: Boolean
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = isEnabled,
            colors = SwitchDefaults.colors(
                checkedThumbColor = colors.thumbColor,
                uncheckedThumbColor = colors.thumbColor,
                checkedTrackColor = colors.trackColorChecked,
                uncheckedTrackColor = colors.trackColorUnchecked,
                disabledCheckedThumbColor = colors.thumbColor.copy(alpha = 0.5f),
                disabledUncheckedThumbColor = colors.thumbColor.copy(alpha = 0.5f),
                disabledCheckedTrackColor = colors.trackColorChecked.copy(alpha = 0.5f),
                disabledUncheckedTrackColor = colors.trackColorUnchecked.copy(alpha = 0.5f)
            )
        )
    }
}