@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KSwitch

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KSwitchColors")
)
public typealias KSwitchColors = com.farimarwat.krossui.KSwitchColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KSwitchDefaults")
)
public typealias KSwitchDefaults = com.farimarwat.krossui.KSwitchDefaults

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KSwitch(checked = checked, onCheckedChange = onCheckedChange, modifier = modifier, enabled = isEnabled, colors = colors)",
        "androidx.compose.ui.unit.dp"
    )
)
@Composable
public fun KSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    colors: KSwitchColors = KSwitchDefaults.colors(),
    isEnabled: Boolean = true
): Unit = com.farimarwat.krossui.KSwitch(
    checked = checked,
    onCheckedChange = onCheckedChange,
    modifier = modifier,
    enabled = isEnabled,
    colors = colors
)
