package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A slider that renders the native control on each platform
 * (Material 3 slider on Android, UISlider on iOS).
 *
 * @param value The current value, between [minValue] and [maxValue].
 * @param onValueChange Called with the new value while the user drags.
 * @param modifier The [Modifier] applied to the slider.
 * @param minValue The lowest selectable value.
 * @param maxValue The highest selectable value.
 * @param colors The slider colors. See [KSliderDefaults.colors].
 */
@Composable
public expect fun KSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    minValue: Float = 0f,
    maxValue: Float = 1f,
    colors: KSliderColors = KSliderDefaults.colors()
)
