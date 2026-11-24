@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KDatePicker

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.runtime.Composable

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KDatePickerColors")
)
public typealias KDatePickerColors = com.farimarwat.krossui.KDatePickerColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KDatePickerDefaults")
)
public typealias KDatePickerDefaults = com.farimarwat.krossui.KDatePickerDefaults

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KDatePicker(show = show, initialDate = initialDate, onDateSelected = onDateSelected, onDismiss = onDismiss, colors = colors)",
        "androidx.compose.ui.unit.dp"
    )
)
@Composable
public fun KDatePicker(
    show: Boolean,
    initialDate: Long,
    colors: KDatePickerColors = KDatePickerDefaults.colors(),
    onDismiss: () -> Unit = {},
    onDateSelected: (Long) -> Unit
): Unit = com.farimarwat.krossui.KDatePicker(
    show = show,
    initialDate = initialDate,
    onDateSelected = onDateSelected,
    onDismiss = onDismiss,
    colors = colors
)
