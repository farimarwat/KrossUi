package com.farimarwat.krossui.components.KTabBar

import org.jetbrains.compose.resources.DrawableResource

/**
 * Represents a single tab item for [KTabBar].
 *
 * @param title The text label displayed on the tab.
 * @param androidIcon The icon resource used for Android.
 * @param iosIcon The SF Symbol name or asset name used for iOS.
 */
data class KTabItem(
    val title: String,
    val androidIcon: DrawableResource,
    val iosIcon: String
)
