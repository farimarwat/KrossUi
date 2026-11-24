package com.farimarwat.krossui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
public actual fun KProgressIndicator(modifier: Modifier, size: Dp, color: Color) {
    CircularProgressIndicator(
        color = color,
        strokeWidth = 4.dp,
        modifier = modifier.size(size)
    )
}
