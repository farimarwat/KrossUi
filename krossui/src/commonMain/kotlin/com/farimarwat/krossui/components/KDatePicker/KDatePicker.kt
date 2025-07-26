package com.farimarwat.krossui.components.KDatePicker

import androidx.compose.runtime.Composable

@Composable
expect fun KDatePicker(
    show: Boolean,
    initialDate:Long,
    colors: KDatePickerColors = KDatePickerDefaults.colors(),
    onDismiss:()->Unit={},
    onDateSelected:(Long)->Unit
)