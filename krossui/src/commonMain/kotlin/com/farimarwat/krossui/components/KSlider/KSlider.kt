package com.farimarwat.krossui.components.KSlider

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun KSlider(
    modifier: Modifier = Modifier,
    value:Float = 0f,
    minValue:Float = 0f,
    maxValue: Float = 1f,
    colors: KSliderColors = KSliderDefaults.colors(),
    onValueChanged:(Float)-> Unit={},
)