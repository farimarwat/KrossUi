package com.farimarwat.krossui.components.KSwitch

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

/**
 * A cross-platform composable switch component used to toggle between two states (on/off).
 *
 * @param modifier The modifier to be applied to the switch layout.
 * @param checked Whether the switch is currently checked (on).
 * @param onCheckedChange Callback that is triggered when the checked state changes.
 * @param colors The color configuration for the switch, including thumb and track colors.
 * @param isEnabled Whether the switch is enabled and interactive.
 */
@Composable
expect fun KSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    colors: KSwitchColors = KSwitchDefaults.colors(),
    isEnabled: Boolean = true
)