package com.farimarwat.krossui

import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
public actual fun KSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier,
    minValue: Float,
    maxValue: Float,
    colors: KSliderColors
) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        valueRange = minValue..maxValue,
        modifier = modifier,
        colors = SliderDefaults.colors(
            activeTrackColor = colors.activeTrackColor,
            inactiveTrackColor = colors.inactiveTrackColor,
            thumbColor = colors.thumbColor,
            disabledActiveTrackColor = colors.disabledActiveTrackColor,
            disabledInactiveTrackColor = colors.disabledInactiveTrackColor,
            disabledThumbColor = colors.disabledThumbColor
        )
    )
}
