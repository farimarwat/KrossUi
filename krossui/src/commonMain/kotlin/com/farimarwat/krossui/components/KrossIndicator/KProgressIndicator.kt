package com.farimarwat.krossui.components.KrossIndicator

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
expect fun KProgressIndicator(
    size: Double = KProgressIndicatorDefaults.DefaultSize,
    color: Color = KProgressIndicatorDefaults.colors().color
)