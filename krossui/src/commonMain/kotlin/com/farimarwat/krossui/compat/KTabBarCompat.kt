@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KTabBar

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.PlatformColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KTabBarColors")
)
public typealias KTabBarColors = com.farimarwat.krossui.KTabBarColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KTabItem")
)
public typealias KTabItem = com.farimarwat.krossui.KTabItem

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KTabBarDefaults")
)
public object KTabBarDefaults {
    public fun colors(
        backgroundColor: Color = PlatformColors.systemBackground,
        selectedContentColor: Color = PlatformColors.systemBlue,
        unselectedContentColor: Color = PlatformColors.systemGray
    ): KTabBarColors = KTabBarColors(
        containerColor = backgroundColor,
        selectedContentColor = selectedContentColor,
        unselectedContentColor = unselectedContentColor
    )
}

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KTabBar(tabs = tabs, selectedIndex = selectedIndex, onTabClick = onTabClick, modifier = modifier, colors = colors)",
        "androidx.compose.ui.unit.dp"
    )
)
@Composable
public fun KTabBar(
    modifier: Modifier = Modifier,
    tabs: List<KTabItem>,
    selectedIndex: Int,
    onTabClick: (Int) -> Unit,
    colors: KTabBarColors = KTabBarDefaults.colors()
): Unit = com.farimarwat.krossui.KTabBar(
    tabs = tabs,
    selectedIndex = selectedIndex,
    onTabClick = onTabClick,
    modifier = modifier,
    colors = colors
)
