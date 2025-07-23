package com.farimarwat.krossui.components.KTabBar

import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

object KTabBarDefaults {
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