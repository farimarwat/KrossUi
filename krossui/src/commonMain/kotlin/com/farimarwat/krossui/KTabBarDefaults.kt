package com.farimarwat.krossui

import androidx.compose.ui.graphics.Color

/**
 * Default values used by [KTabBar].
 *
 * Every default is unspecified: the platform draws its native look (Material theme on Android,
 * system appearance on iOS). Pass a value only to override it.
 */
public object KTabBarDefaults {
    /** Returns [KTabBarColors]. Unspecified colors use the platform default. */
    public fun colors(
        containerColor: Color = Color.Unspecified,
        selectedContentColor: Color = Color.Unspecified,
        unselectedContentColor: Color = Color.Unspecified
    ): KTabBarColors = KTabBarColors(
        containerColor = containerColor,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor
    )
}
