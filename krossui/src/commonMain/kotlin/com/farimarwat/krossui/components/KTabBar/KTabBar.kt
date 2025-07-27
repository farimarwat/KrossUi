package com.farimarwat.krossui.components.KTabBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A cross-platform composable tab bar component.
 *
 * Displays a row of tabs allowing users to switch between views or content sections.
 *
 * @param modifier The modifier to be applied to the TabBar layout.
 * @param tabs A list of [KTabItem] representing each tab.
 * @param selectedIndex The currently selected tab index.
 * @param onTabClick Lambda to be invoked when a tab is clicked, with the clicked tab's index.
 * @param colors The color scheme for the tab bar, see [KTabBarDefaults.colors].
 */
@Composable
expect fun KTabBar(
    modifier: Modifier = Modifier,
    tabs: List<KTabItem>,
    selectedIndex: Int,
    onTabClick: (Int) -> Unit,
    colors: KTabBarColors = KTabBarDefaults.colors()
)