package com.farimarwat.krossui.components.KTabBar

import androidx.compose.ui.graphics.Color

/**
 * Defines the color scheme for [KTabBar].
 *
 * @param backgroundColor The background color of the tab bar.
 * @param selectedContentColor The color of the selected tab's icon and text.
 * @param unselectedContentColor The color of the unselected tabs' icon and text.
 */
data class KTabBarColors(
    val backgroundColor: Color,
    val selectedContentColor: Color,
    val unselectedContentColor: Color
)
