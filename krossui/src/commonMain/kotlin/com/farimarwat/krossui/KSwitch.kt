package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * An on/off switch that renders the native control on each platform
 * (Material 3 switch on Android, UISwitch on iOS).
 *
 * @param checked Whether the switch is on.
 * @param onCheckedChange Called with the new state when the user toggles the switch.
 * @param modifier The [Modifier] applied to the switch.
 * @param enabled Whether the switch can be toggled.
 * @param contentDescription Text read by TalkBack / VoiceOver; describe what the switch controls.
 * @param colors The switch colors. See [KSwitchDefaults.colors].
 */
@Composable
public expect fun KSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentDescription: String? = null,
    colors: KSwitchColors = KSwitchDefaults.colors()
)
