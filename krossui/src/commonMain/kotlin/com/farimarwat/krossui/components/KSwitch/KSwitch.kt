package com.farimarwat.krossui.components.KSwitch

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

@Composable
expect fun KSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    colors: KSwitchColors = KSwitchDefaults.colors(),
    isEnabled: Boolean = true
)