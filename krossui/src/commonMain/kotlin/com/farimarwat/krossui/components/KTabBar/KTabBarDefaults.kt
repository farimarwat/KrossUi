package com.farimarwat.krossui.components.KTabBar

import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

/**
 * Provides default color values for [KTabBar].
 */
object KTabBarDefaults {

    /**
     * Returns a default [KTabBarColors] instance with optional overrides.
     *
     * @param backgroundColor The background color of the tab bar.
     * @param selectedContentColor The color of the selected tab's icon and text.
     * @param unselectedContentColor The color of the unselected tabs' icon and text.
     * @return A configured [KTabBarColors] instance.
     */
    fun colors(
        backgroundColor: Color = PlatformColors.systemBackground,
        selectedContentColor: Color = PlatformColors.systemBlue,
        unselectedContentColor: Color = PlatformColors.systemGray
    ): KTabBarColors = KTabBarColors(
        backgroundColor = backgroundColor,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor
    )
}