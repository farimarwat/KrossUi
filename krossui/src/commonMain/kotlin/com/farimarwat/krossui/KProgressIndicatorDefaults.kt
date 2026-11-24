package com.farimarwat.krossui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Default values and factory methods for [KProgressIndicator].
 */
public object KProgressIndicatorDefaults {

    /**
     * Creates a [KProgressIndicatorColors] instance with the specified color.
     *
     * @param color The color for the progress indicator. Defaults to [PlatformColors.systemBlue].
     * @return A [KProgressIndicatorColors] instance with the given color.
     */
    public fun colors(color: Color = PlatformColors.systemBlue): KProgressIndicatorColors =
        KProgressIndicatorColors(color)

    /** Default diameter. */
    public val Size: Dp = 36.dp
}
