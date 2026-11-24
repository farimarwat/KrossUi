package com.farimarwat.krossui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * Default values used by [KProgressIndicator].
 *
 * Every default is unspecified: the platform draws its native look (Material theme on Android,
 * system appearance on iOS). Pass a value only to override it.
 */
public object KProgressIndicatorDefaults {
    /** Returns [KProgressIndicatorColors]. Unspecified color uses the platform default. */
    public fun colors(color: Color = Color.Unspecified): KProgressIndicatorColors = KProgressIndicatorColors(color)

    /** Diameter; unspecified = platform default size. */
    public val Size: Dp = Dp.Unspecified
}
