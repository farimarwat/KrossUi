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
    enabled: Boolean,
    contentDescription: String?,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    onValueChangeFinished: (() -> Unit)?,
    colors: KSliderColors
) {
    Slider(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.labelled(contentDescription),
        enabled = enabled,
        valueRange = valueRange,
        steps = steps,
        onValueChangeFinished = onValueChangeFinished,
        colors = SliderDefaults.colors(
            thumbColor = colors.thumbColor,
            activeTrackColor = colors.activeTrackColor,
            inactiveTrackColor = colors.inactiveTrackColor,
            disabledThumbColor = colors.disabledThumbColor,
            disabledActiveTrackColor = colors.disabledActiveTrackColor,
            disabledInactiveTrackColor = colors.disabledInactiveTrackColor
        )
    )
}
