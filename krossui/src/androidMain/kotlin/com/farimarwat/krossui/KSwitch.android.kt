package com.farimarwat.krossui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
public actual fun KSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    colors: KSwitchColors
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Switch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
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
