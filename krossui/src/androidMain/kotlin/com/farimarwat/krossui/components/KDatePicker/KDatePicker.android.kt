package com.farimarwat.krossui.components.KDatePicker

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun KDatePicker(
    show: Boolean,
    initialDate: Long,
    colors: KDatePickerColors,
    onDismiss: () -> Unit,
    onDateSelected: (Long) -> Unit
) {

    val datePickerState = rememberDatePickerState()
    var currentDate = remember{
        datePickerState.selectedDateMillis ?: 0
    }
    if (show) {
        Popup(
            onDismissRequest = onDismiss,
            alignment = Alignment.Center,
            properties = PopupProperties(
                clippingEnabled = true
            )
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
            ) {
                DatePicker(
                    state = datePickerState,
                    colors = DatePickerDefaults.colors(
                        containerColor = colors.containerColor,
                        dayContentColor = colors.contentColor
                    )
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colors.footerContainerColor),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        onDismiss()
                    }) {
                        Text(
                            text = "Cancel",
                            color = colors.footerContentColor
                        )
                    }
                    TextButton(onClick = {
                        onDateSelected(datePickerState.selectedDateMillis ?: 0)
                    }) {
                        Text(
                            text = "Done",
                            color = colors.footerContentColor
                        )
                    }
                }
            }

        }

    }
}