package com.farimarwat.krossui.components.KSlider

import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
actual fun KSlider(
    modifier: Modifier,
    value: Float,
    minValue: Float,
    maxValue: Float,
    colors: KSliderColors,
    onValueChanged: (Float) -> Unit
) {
    Slider(
        value = value,
        onValueChange = onValueChanged,
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