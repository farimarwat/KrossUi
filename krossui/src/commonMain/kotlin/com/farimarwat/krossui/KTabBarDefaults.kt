package com.farimarwat.krossui

import androidx.compose.ui.graphics.Color

/**
 * Provides default color values for [KTabBar].
 */
public object KTabBarDefaults {

    /**
     * Returns a default [KTabBarColors] instance with optional overrides.
     *
     * @param containerColor The background color of the tab bar.
     * @param selectedContentColor The color of the selected tab's icon and text.
     * @param unselectedContentColor The color of the unselected tabs' icon and text.
     * @return A configured [KTabBarColors] instance.
     */
    public fun colors(
        containerColor: Color = PlatformColors.systemBackground,
        selectedContentColor: Color = PlatformColors.systemBlue,
        unselectedContentColor: Color = PlatformColors.systemGray
    ): KTabBarColors = KTabBarColors(
        containerColor = containerColor,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor
    )
}
