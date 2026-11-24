@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KPicker

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KPickerColors")
)
public typealias KPickerColors = com.farimarwat.krossui.KPickerColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KPickerDefaults")
)
public typealias KPickerDefaults = com.farimarwat.krossui.KPickerDefaults

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KPicker(items = items, onItemPicked = onItemPicked, modifier = modifier, colors = colors)",
        "androidx.compose.ui.unit.dp"
    )
)
@Composable
public fun KPicker(
    modifier: Modifier = Modifier,
    items: List<String>,
    colors: KPickerColors = KPickerDefaults.colors(),
    onItemPicked: (String) -> Unit
): Unit = com.farimarwat.krossui.KPicker(
    items = items,
    onItemPicked = onItemPicked,
    modifier = modifier,
    colors = colors
)
