package com.farimarwat.krossui.components.KSlider

import androidx.compose.ui.graphics.Color

/**
 * Holds the color configuration for different parts of the [KSlider].
 *
 * @param activeTrackColor Color of the filled part of the track when enabled.
 * @param inactiveTrackColor Color of the unfilled part of the track when enabled.
 * @param thumbColor Color of the thumb when enabled.
 * @param disabledActiveTrackColor Color of the filled track when disabled.
 * @param disabledInactiveTrackColor Color of the unfilled track when disabled.
 * @param disabledThumbColor Color of the thumb when disabled.
 */
data class KSliderColors(
    val activeTrackColor: Color,
    val inactiveTrackColor: Color,
    val thumbColor: Color,
    val disabledActiveTrackColor: Color,
    val disabledInactiveTrackColor: Color,
    val disabledThumbColor: Color
)