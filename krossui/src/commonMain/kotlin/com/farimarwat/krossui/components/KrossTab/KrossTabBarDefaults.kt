package com.farimarwat.krossui.components.KrossTab

import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

object KrossTabBarDefaults {
    fun colors(
        backgroundColor: Color = PlatformColors.systemBackground,
        selectedContentColor: Color = PlatformColors.systemBlue,
        unselectedContentColor: Color = PlatformColors.systemGray
    ): KrossTabBarColors = KrossTabBarColors(
        backgroundColor = backgroundColor,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor
    )
}