package com.farimarwat.krossui.components.KProgressIndicator

import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors


/**
 * Default values and factory methods for [KProgressIndicator].
 */
object KProgressIndicatorDefaults {

    /**
     * Creates a [KProgressIndicatorColors] instance with the specified color.
     *
     * @param color The color for the progress indicator. Defaults to [PlatformColors.systemBlue].
     * @return A [KProgressIndicatorColors] instance with the given color.
     */
    fun colors(
        color: Color = PlatformColors.systemBlue
    ): KProgressIndicatorColors = KProgressIndicatorColors(color)

    /**
     * Default size of the progress indicator in dp.
     */
    val DefaultSize: Double = 36.0
}