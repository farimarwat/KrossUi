package com.farimarwat.krossui.components.KMenu

import org.jetbrains.compose.resources.DrawableResource

/**
 * Represents a single item in the [KMenu].
 *
 * @property title Text label of the menu item.
 * @property androidIcon Optional icon to display on Android.
 * @property iosIcon Optional SF Symbol name to display on iOS.
 * @property enabled Determines if the item is enabled (clickable).
 */
data class KMenuItem(
    val title: String,
    val androidIcon: DrawableResource? = null,
    val iosIcon: String? = null,
    val enabled: Boolean = true
)

