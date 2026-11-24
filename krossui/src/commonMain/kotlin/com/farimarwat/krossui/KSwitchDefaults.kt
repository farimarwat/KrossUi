package com.farimarwat.krossui

import androidx.compose.ui.graphics.Color

/**
 * Default values used by [KSwitch].
 *
 * Every default is unspecified: the platform draws its native look (Material theme on Android,
 * system appearance on iOS). Pass a value only to override it.
 */
public object KSwitchDefaults {
    /** Returns [KSwitchColors]. Unspecified colors use the platform default. */
    public fun colors(
        thumbColor: Color = Color.Unspecified,
        trackColorChecked: Color = Color.Unspecified,
        trackColorUnchecked: Color = Color.Unspecified
    ): KSwitchColors = KSwitchColors(
        thumbColor = thumbColor,
        trackColorChecked = trackColorChecked,
        trackColorUnchecked = trackColorUnchecked
    )
}
