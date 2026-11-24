package com.farimarwat.krossui

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import kotlinx.datetime.LocalDate

/** Material 3 date picker in Material's own dialog (sizes itself to the screen). */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
public actual fun KDatePicker(
    show: Boolean,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit,
    initialDate: LocalDate?,
    colors: KDatePickerColors
) {
    if (!show) return

    val startDate = initialDate ?: today()
    // Material works in UTC-midnight milliseconds; the state is saved across configuration changes
    val datePickerState = rememberDatePickerState(initialSelectedDateMillis = startDate.toUtcMillis())
    val pickerColors = DatePickerDefaults.colors(
        containerColor = colors.containerColor,
        dayContentColor = colors.contentColor
    )
    val buttonColors = ButtonDefaults.textButtonColors(contentColor = colors.footerContentColor)

    DatePickerDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    onDateSelected(datePickerState.selectedDateMillis?.let(::localDateFromUtcMillis) ?: startDate)
                },
                colors = buttonColors
            ) {
                Text("Done")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss, colors = buttonColors) {
                Text("Cancel")
            }
        },
        colors = pickerColors
    ) {
        DatePicker(state = datePickerState, colors = pickerColors)
    }
}
