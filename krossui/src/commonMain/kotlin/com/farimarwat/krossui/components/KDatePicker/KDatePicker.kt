package com.farimarwat.krossui.components.KDatePicker

import androidx.compose.runtime.Composable

@Composable
expect fun KDatePicker(
    show: Boolean,
    initialDate:Long,
    onDismiss:()->Unit={},
    onDateSelected:(Long)->Unit
)