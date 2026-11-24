package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDate

/**
 * A native date picker shown in a dialog
 * (Material 3 date picker on Android, wheel UIDatePicker on iOS).
 *
 * Dates are calendar dates ([LocalDate]) without a time or time zone, so the picked day is the same
 * on every device.
 *
 * @param show Whether the picker is visible.
 * @param onDateSelected Called with the chosen date when the user confirms.
 * @param onDismiss Called when the picker is dismissed without choosing.
 * @param initialDate The date selected when the picker opens; `null` = today.
 * @param colors The picker colors. See [KDatePickerDefaults.colors].
 */
@Composable
public expect fun KDatePicker(
    show: Boolean,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit = {},
    initialDate: LocalDate? = null,
    colors: KDatePickerColors = KDatePickerDefaults.colors()
)
