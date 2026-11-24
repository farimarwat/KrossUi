package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Provides default values and factory methods for [KMenu] styling.
 */
public object KMenuDefaults {

    /**
     * Returns a default [KMenuColors] instance with system-based colors.
     *
     * @param containerColor The menu's background color.
     * @param contentColor The active content color (text/icon).
     * @param disabledContentColor The color used for disabled menu items.
     */
    @Composable
    public fun colors(
        containerColor: Color = PlatformColors.systemGray,
        contentColor: Color = PlatformColors.label,
        disabledContentColor: Color = contentColor.copy(alpha = 0.4f)
    ): KMenuColors = KMenuColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor
    )
}
