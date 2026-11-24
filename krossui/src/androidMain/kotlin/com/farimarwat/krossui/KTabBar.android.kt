package com.farimarwat.krossui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.takeOrElse
import org.jetbrains.compose.resources.painterResource

@Composable
public actual fun KTabBar(
    tabs: List<KTabItem>,
    selectedIndex: Int,
    onTabClick: (Int) -> Unit,
    modifier: Modifier,
    colors: KTabBarColors
) {
    NavigationBar(
        modifier = modifier,
        containerColor = colors.containerColor.takeOrElse { NavigationBarDefaults.containerColor }
    ) {
        val itemColors = NavigationBarItemDefaults.colors(
            selectedIconColor = colors.selectedContentColor,
            selectedTextColor = colors.selectedContentColor,
            unselectedIconColor = colors.unselectedContentColor,
            unselectedTextColor = colors.unselectedContentColor
        )
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onTabClick(index) },
                icon = { Icon(painter = painterResource(tab.androidIcon), contentDescription = null) },
                label = { Text(tab.title) },
                colors = itemColors
            )
        }
    }
}
