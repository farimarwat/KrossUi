package com.farimarwat.krossui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import kotlinx.datetime.LocalDate

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
    val footerButtonColors = ButtonDefaults.textButtonColors(contentColor = colors.footerContentColor)

    Popup(
        onDismissRequest = onDismiss,
        alignment = Alignment.Center,
        properties = PopupProperties(clippingEnabled = true)
    ) {
        Column(modifier = Modifier.clip(RoundedCornerShape(16.dp))) {
            DatePicker(state = datePickerState, colors = pickerColors)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(colors.footerContainerColor.takeOrElse { pickerColors.containerColor }),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismiss, colors = footerButtonColors) {
                    Text("Cancel")
                }
                TextButton(
                    onClick = {
                        onDateSelected(datePickerState.selectedDateMillis?.let(::localDateFromUtcMillis) ?: startDate)
                    },
                    colors = footerButtonColors
                ) {
                    Text("Done")
                }
            }
        }
    }
}
