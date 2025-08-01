package com.farimarwat.krossui.components.KTabBar

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource

@Composable
actual fun KTabBar(
    modifier: Modifier,
    tabs: List<KTabItem>,
    selectedIndex: Int,
    onTabClick: (Int) -> Unit,
    colors: KTabBarColors
) {
    NavigationBar(
        modifier = modifier
    ) {
        tabs.forEachIndexed { index, tab ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(tab.androidIcon),
                        contentDescription = tab.title
                    )
                },
                label = { Text(tab.title) },
                selected = selectedIndex == index,
                onClick = { onTabClick(index) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colors.selectedContentColor,
                    unselectedIconColor = colors.unselectedContentColor,
                    selectedTextColor = colors.selectedContentColor,
                    unselectedTextColor = colors.unselectedContentColor
                )
            )
        }
    }
}