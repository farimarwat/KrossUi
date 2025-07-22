package com.farimarwat.krossui.components.KrossTab

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIColor
import platform.UIKit.UIImage
import platform.UIKit.UITabBar
import platform.UIKit.UITabBarDelegateProtocol
import platform.UIKit.UITabBarItem
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun KrossTabBar(
    modifier: Modifier,
    tabs: List<KrossTabItem>,
    selectedIndex: Int,
    onTabClick: (Int) -> Unit,
    colors: KrossTabBarColors
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
    LaunchedEffect(Unit) {
        tabBar.barTintColor = colors.backgroundColor.toUiColor()
        tabBar.tintColor = colors.selectedContentColor.toUiColor()
        tabBar.unselectedItemTintColor = colors.unselectedContentColor.toUiColor()
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
        factory = {
            tabBar.apply {
                setNeedsLayout()
                layoutIfNeeded()
            }
                  },
        modifier =  modifier
            .wrapContentHeight()
        .defaultMinSize(minHeight = 83.dp)
    )
}