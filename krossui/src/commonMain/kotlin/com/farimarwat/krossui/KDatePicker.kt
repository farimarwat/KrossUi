package com.farimarwat.krossui

import androidx.compose.runtime.Composable

/**
 * A native date picker shown in a dialog
 * (Material 3 date picker on Android, wheel UIDatePicker on iOS).
 *
 * @param show Whether the picker is visible.
 * @param initialDate The date selected when the picker opens, in epoch milliseconds.
 * @param onDateSelected Called with the chosen date (epoch milliseconds) when the user confirms.
 * @param onDismiss Called when the picker is dismissed without choosing.
 * @param colors The picker colors. See [KDatePickerDefaults.colors].
 */
@Composable
public expect fun KDatePicker(
    show: Boolean,
    initialDate: Long,
    onDateSelected: (Long) -> Unit,
    onDismiss: () -> Unit = {},
    colors: KDatePickerColors = KDatePickerDefaults.colors()
)
