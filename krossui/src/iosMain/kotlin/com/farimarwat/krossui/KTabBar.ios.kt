package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.utils.NativeView
import com.farimarwat.krossui.utils.toUiColorOrNull
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGSizeMake
import platform.UIKit.UIImage
import platform.UIKit.UITabBar
import platform.UIKit.UITabBarDelegateProtocol
import platform.UIKit.UITabBarItem
import platform.darwin.NSObject

@Composable
public actual fun KTabBar(
    tabs: List<KTabItem>,
    selectedIndex: Int,
    onTabClick: (Int) -> Unit,
    modifier: Modifier,
    colors: KTabBarColors
) {
    val currentOnTabClick by rememberUpdatedState(onTabClick)
    // UITabBar holds its delegate weakly; remember keeps it alive
    val delegate = remember {
        object : NSObject(), UITabBarDelegateProtocol {
            override fun tabBar(tabBar: UITabBar, didSelectItem: UITabBarItem) {
                currentOnTabClick(didSelectItem.tag.toInt())
            }
        }
    }
    // Native bar height; UIKit already includes the bottom safe area (home indicator) in it
    val barHeight = remember { UITabBar().sizeThatFits(CGSizeMake(0.0, 0.0)).useContents { height } }
    // Last tabs given to UITabBar, so items are only rebuilt when tabs change
    val shown = remember { ShownTabs() }

    NativeView(
        factory = { UITabBar().apply { this.delegate = delegate } },
        update = { tabBar ->
            if (shown.tabs != tabs) {
                tabBar.setItems(
                    tabs.mapIndexed { index, tab ->
                        UITabBarItem(
                            title = tab.title,
                            image = UIImage.systemImageNamed(tab.iosIcon),
                            tag = index.toLong()
                        )
                    },
                    animated = false
                )
                shown.tabs = tabs
            }
            val selected = tabBar.items?.getOrNull(selectedIndex) as? UITabBarItem
            if (tabBar.selectedItem != selected) tabBar.selectedItem = selected

            tabBar.barTintColor = colors.containerColor.toUiColorOrNull()
            // tintColor cannot be reset to null from Kotlin; set it only when a color is given
            colors.selectedContentColor.toUiColorOrNull()?.let { tabBar.tintColor = it }
            tabBar.unselectedItemTintColor = colors.unselectedContentColor.toUiColorOrNull()
        },
        modifier = modifier,
        // Fills the width; height is the native bar height
        preferredSize = { DpSize(Dp.Unspecified, barHeight.dp) }
    )
}

private class ShownTabs {
    var tabs: List<KTabItem>? = null
}
