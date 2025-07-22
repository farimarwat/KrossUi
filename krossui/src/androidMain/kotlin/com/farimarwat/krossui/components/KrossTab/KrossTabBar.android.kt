package com.farimarwat.krossui.components.KrossTab

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import org.jetbrains.compose.resources.painterResource

@Composable
actual fun KrossTabBar(
    tabs: List<KrossTabItem>,
    selectedIndex: Int,
    onTabClick: (Int) -> Unit,
    modifier: Modifier
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
                onClick = { onTabClick(index) }
            )
        }
    }
}