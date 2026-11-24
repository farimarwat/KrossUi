package com.farimarwat.krossui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Defines the color scheme for [KTabBar].
 *
 * @param containerColor The background color of the tab bar.
 * @param selectedContentColor The color of the selected tab's icon and text.
 * @param unselectedContentColor The color of the unselected tabs' icon and text.
 */
@Immutable
public data class KTabBarColors(
    val containerColor: Color,
    val selectedContentColor: Color,
    val unselectedContentColor: Color
)
