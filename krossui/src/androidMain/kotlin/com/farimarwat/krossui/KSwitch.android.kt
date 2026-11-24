package com.farimarwat.krossui

import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public actual fun KSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    contentDescription: String?,
    colors: KSwitchColors
) {
    Switch(
        checked = checked,
        onCheckedChange = onCheckedChange,
        modifier = modifier.labelled(contentDescription),
        enabled = enabled,
        // copy() keeps the theme value for every unspecified color
        colors = SwitchDefaults.colors().copy(
            checkedThumbColor = colors.thumbColor,
            uncheckedThumbColor = colors.thumbColor,
            checkedTrackColor = colors.trackColorChecked,
            uncheckedTrackColor = colors.trackColorUnchecked
        )
    )
}
