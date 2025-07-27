package com.farimarwat.krossui.components.KMenu

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

/**
 * Provides default values and factory methods for [KMenu] styling.
 */
object KMenuDefaults {

    /**
     * Returns a default [KMenuColors] instance with system-based colors.
     *
     * @param backgroundColor The menu's background color.
     * @param contentColor The active content color (text/icon).
     * @param disabledContentColor The color used for disabled menu items.
     */
    @Composable
    fun colors(
        backgroundColor: Color = PlatformColors.systemGray,
        contentColor: Color = PlatformColors.label,
        disabledContentColor: Color = contentColor.copy(alpha = 0.4f)
    ): KMenuColors = KMenuColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor
    )
}

