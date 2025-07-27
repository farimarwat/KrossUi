package com.farimarwat.krossui.components.KSlider

import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

/**
 * Contains default values and helper functions for configuring the [KSlider].
 */
object KSliderDefaults {

    /**
     * Returns a [KSliderColors] instance with provided or default color values.
     *
     * @param activeTrackColor Color of the filled track when enabled.
     * @param inactiveTrackColor Color of the unfilled track when enabled.
     * @param thumbColor Color of the thumb when enabled.
     * @param disabledActiveTrackColor Color of the filled track when disabled.
     * @param disabledInactiveTrackColor Color of the unfilled track when disabled.
     * @param disabledThumbColor Color of the thumb when disabled.
     */
    fun colors(
        activeTrackColor: Color = PlatformColors.systemBlue,
        inactiveTrackColor: Color = PlatformColors.systemGray,
        thumbColor: Color = Color.White,
        disabledActiveTrackColor: Color = activeTrackColor.copy(alpha = 0.3f),
        disabledInactiveTrackColor: Color = inactiveTrackColor.copy(alpha = 0.3f),
        disabledThumbColor: Color = thumbColor.copy(alpha = 0.3f)
    ): KSliderColors = KSliderColors(
        activeTrackColor = activeTrackColor,
        inactiveTrackColor = inactiveTrackColor,
        thumbColor = thumbColor,
        disabledActiveTrackColor = disabledActiveTrackColor,
        disabledInactiveTrackColor = disabledInactiveTrackColor,
        disabledThumbColor = disabledThumbColor
    )
}
