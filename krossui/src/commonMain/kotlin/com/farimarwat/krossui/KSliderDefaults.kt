package com.farimarwat.krossui

import androidx.compose.ui.graphics.Color

/**
 * Default values used by [KSlider].
 *
 * Every default is unspecified: the platform draws its native look (Material theme on Android,
 * system appearance on iOS). Pass a value only to override it.
 */
public object KSliderDefaults {
    /** Returns [KSliderColors]. Unspecified colors use the platform default. */
    public fun colors(
        activeTrackColor: Color = Color.Unspecified,
        inactiveTrackColor: Color = Color.Unspecified,
        thumbColor: Color = Color.Unspecified,
        disabledActiveTrackColor: Color = Color.Unspecified,
        disabledInactiveTrackColor: Color = Color.Unspecified,
        disabledThumbColor: Color = Color.Unspecified
    ): KSliderColors = KSliderColors(
        activeTrackColor = activeTrackColor,
        inactiveTrackColor = inactiveTrackColor,
        thumbColor = thumbColor,
        disabledActiveTrackColor = disabledActiveTrackColor,
        disabledInactiveTrackColor = disabledInactiveTrackColor,
        disabledThumbColor = disabledThumbColor
    )
}
