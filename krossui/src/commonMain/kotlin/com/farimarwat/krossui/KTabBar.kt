package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A bottom tab bar that renders the native control on each platform
 * (Material 3 navigation bar on Android, UITabBar on iOS — Liquid Glass on iOS 26+).
 *
 * @param tabs The tabs to show.
 * @param selectedIndex The index of the selected tab.
 * @param onTabClick Called with the index of the tapped tab.
 * @param modifier The [Modifier] applied to the tab bar.
 * @param colors The tab bar colors. See [KTabBarDefaults.colors].
 */
@Composable
public expect fun KTabBar(
    tabs: List<KTabItem>,
    selectedIndex: Int,
    onTabClick: (Int) -> Unit,
    modifier: Modifier = Modifier,
    colors: KTabBarColors = KTabBarDefaults.colors()
)
