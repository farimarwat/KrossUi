package com.farimarwat.krossui.components.KrossTab

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun KrossTabBar(
    modifier: Modifier = Modifier,
    tabs: List<KrossTabItem>,
    selectedIndex: Int,
    onTabClick: (Int) -> Unit,
    colors: KrossTabBarColors = KrossTabBarDefaults.colors()
)