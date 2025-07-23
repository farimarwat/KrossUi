package com.farimarwat.krossui.components.KProgressIndicator

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
expect fun KProgressIndicator(
    size: Double = KProgressIndicatorDefaults.DefaultSize,
    color: Color = KProgressIndicatorDefaults.colors().color
)