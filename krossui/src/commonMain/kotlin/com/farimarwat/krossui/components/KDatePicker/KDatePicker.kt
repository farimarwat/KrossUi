package com.farimarwat.krossui.components.KDatePicker

import androidx.compose.runtime.Composable

/**
 * A cross-platform composable date picker dialog.
 *
 * @param show Whether the dialog should be shown.
 * @param initialDate The initially selected date in milliseconds.
 * @param colors Customizable color configuration for the date picker.
 * @param onDismiss Callback invoked when the dialog is dismissed.
 * @param onDateSelected Callback invoked with the selected date in milliseconds.
 */
@Composable
expect fun KDatePicker(
    show: Boolean,
    initialDate: Long,
    colors: KDatePickerColors = KDatePickerDefaults.colors(),
    onDismiss: () -> Unit = {},
    onDateSelected: (Long) -> Unit
)