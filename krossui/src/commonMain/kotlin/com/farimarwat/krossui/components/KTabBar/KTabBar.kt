package com.farimarwat.krossui.components.KTabBar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun KTabBar(
    modifier: Modifier = Modifier,
    tabs: List<KTabItem>,
    selectedIndex: Int,
    onTabClick: (Int) -> Unit,
    colors: KTabBarColors = KTabBarDefaults.colors()
)