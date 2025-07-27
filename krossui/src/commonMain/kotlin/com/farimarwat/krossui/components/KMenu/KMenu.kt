package com.farimarwat.krossui.components.KMenu

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource

/**
 * A platform-aware popup menu component for Kotlin Multiplatform apps.
 * It shows a list of selectable menu items with optional platform-specific icons.
 *
 * @param title The title of the menu (displayed on iOS if applicable).
 * @param iOSIcon Optional icon name for iOS (uses SF Symbols).
 * @param androidIcon Optional drawable resource for Android.
 * @param items A list of [KMenuItem] to be displayed in the menu.
 * @param onItemClick Callback triggered when a menu item is selected.
 * @param modifier Modifier for styling and layout.
 * @param colors Color customization for menu background and content.
 */
@Composable
expect fun KMenu(
    title: String = "Hello",
    iOSIcon: String? = null,
    androidIcon: DrawableResource? = null,
    items: List<KMenuItem>,
    onItemClick: (KMenuItem) -> Unit,
    modifier: Modifier = Modifier,
    colors: KMenuColors = KMenuDefaults.colors()
)

