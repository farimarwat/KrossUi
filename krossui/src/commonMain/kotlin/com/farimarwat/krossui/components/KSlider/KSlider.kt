package com.farimarwat.krossui.components.KSlider

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A platform-adaptive slider component for selecting a float value within a specified range.
 *
 * This composable displays a slider UI element with custom colors and value range.
 *
 * @param modifier Modifier to style or position the slider.
 * @param value Current value of the slider.
 * @param minValue Minimum value of the slider.
 * @param maxValue Maximum value of the slider.
 * @param colors Colors used to customize the appearance of the slider.
 * @param onValueChanged Callback function triggered when the slider value changes.
 */
@Composable
expect fun KSlider(
    modifier: Modifier = Modifier,
    value: Float = 0f,
    minValue: Float = 0f,
    maxValue: Float = 1f,
    colors: KSliderColors = KSliderDefaults.colors(),
    onValueChanged: (Float) -> Unit = {},
)