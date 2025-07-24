package com.farimarwat.krossui.components.KMenu

import org.jetbrains.compose.resources.DrawableResource

data class KMenuItem(
    val title: String,
    val androidIcon: DrawableResource? = null,
    val iosIcon: String? = null,
    val enabled: Boolean = true
)
