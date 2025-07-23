package com.farimarwat.krossui.components.KProgressIndicator

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
actual fun KProgressIndicator(
    size: Double,
    color: Color
) {
    CircularProgressIndicator(
        color = color,
        strokeWidth = 4.dp,
        modifier = Modifier.size(size.dp)
    )
}