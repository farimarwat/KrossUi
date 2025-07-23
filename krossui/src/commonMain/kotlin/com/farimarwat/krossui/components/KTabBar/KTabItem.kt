package com.farimarwat.krossui.components.KTabBar

import org.jetbrains.compose.resources.DrawableResource

data class KTabItem(
    val title: String,
    val androidIcon: DrawableResource,
    val iosIcon: String
)
