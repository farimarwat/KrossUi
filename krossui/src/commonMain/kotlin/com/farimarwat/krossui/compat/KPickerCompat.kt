@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KPicker

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    "Moved to com.farimarwat.krossui (KrossUI 2.0); the picker is now controlled by selectedIndex. See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KPicker(items = items, selectedIndex = 0, onSelectedIndexChange = { }, modifier = modifier, colors = colors)"
    )
)
@Composable
public fun KPicker(
    modifier: Modifier = Modifier,
    items: List<String>,
    colors: KPickerColors = KPickerDefaults.colors(),
    onItemPicked: (String) -> Unit
) {
    // 1.x pickers kept their own selection
    var selectedIndex by remember { mutableStateOf(0) }
    com.farimarwat.krossui.KPicker(
        items = items,
        selectedIndex = selectedIndex,
        onSelectedIndexChange = {
            selectedIndex = it
            onItemPicked(items[it])
        },
        modifier = modifier,
        colors = colors
    )
}
