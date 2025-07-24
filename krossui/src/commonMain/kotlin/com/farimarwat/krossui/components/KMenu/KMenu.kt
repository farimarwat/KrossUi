package com.farimarwat.krossui.components.KMenu

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource

@Composable
expect fun KMenu(
    title:String = "Hello",
    iOSIcon:String? = null,
    androidIcon: DrawableResource? = null,
    items: List<KMenuItem>,
    onItemClick: (KMenuItem) -> Unit,
    modifier: Modifier = Modifier,
    colors: KMenuColors = KMenuDefaults.colors()
)
