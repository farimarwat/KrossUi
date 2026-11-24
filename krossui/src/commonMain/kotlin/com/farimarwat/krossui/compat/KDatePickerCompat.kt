@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KDatePicker

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.runtime.Composable
import kotlin.time.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

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
    "Moved to com.farimarwat.krossui (KrossUI 2.0); dates are now kotlinx.datetime.LocalDate. See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KDatePicker(show = show, onDateSelected = { }, onDismiss = onDismiss, colors = colors)"
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
    // 1.x milliseconds are read as UTC calendar dates and returned as UTC midnight
    onDateSelected = { onDateSelected(it.atStartOfDayIn(TimeZone.UTC).toEpochMilliseconds()) },
    onDismiss = onDismiss,
    initialDate = Instant.fromEpochMilliseconds(initialDate).toLocalDateTime(TimeZone.UTC).date,
    colors = colors
)
