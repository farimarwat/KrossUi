package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Default values used by [KMenu].
 *
 * Every default is unspecified: the platform draws its native look (Material theme on Android,
 * system appearance on iOS). Pass a value only to override it.
 */
public object KMenuDefaults {
    /** Returns [KMenuColors]. Unspecified colors use the platform default. */
    @Composable
    public fun colors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified
    ): KMenuColors = KMenuColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor
    )
}
