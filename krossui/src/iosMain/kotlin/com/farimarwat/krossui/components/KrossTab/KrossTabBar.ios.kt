package com.farimarwat.krossui.components.KrossTab

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import platform.UIKit.UIImage
import platform.UIKit.UITabBar
import platform.UIKit.UITabBarDelegateProtocol
import platform.UIKit.UITabBarItem
import platform.darwin.NSObject

@Composable
actual fun KrossTabBar(
    tabs: List<KrossTabItem>,
    selectedIndex: Int,
    onTabClick: (Int) -> Unit,
    modifier: Modifier
) {
    val tabBar = remember { UITabBar() }
    val delegate = remember {
        object : NSObject(), UITabBarDelegateProtocol {
            override fun tabBar(tabBar: UITabBar, didSelectItem: UITabBarItem) {
                val index = tabBar.items?.indexOf(didSelectItem) ?: -1
                if (index != -1) {
                    onTabClick(index)
                }
            }
        }
    }

    // Setup tab bar items
    LaunchedEffect(tabs) {
        val tabBarItems = tabs.mapIndexed { index, tab ->
            UITabBarItem(
                title = tab.title,
                image = UIImage.systemImageNamed(tab.iosIcon),
                tag = index.toLong()
            )
        }
        tabBar.setItems(tabBarItems)
        tabBar.delegate = delegate
    }

    // Update selected tab
    LaunchedEffect(selectedIndex) {
        if (selectedIndex >= 0 && selectedIndex < (tabBar.items?.size ?: 0)) {
            tabBar.selectedItem = tabBar.items?.get(selectedIndex) as? UITabBarItem
        }
    }

    UIKitView(
        factory = { tabBar },
        modifier = modifier
    )
}