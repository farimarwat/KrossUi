package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.DrawableResource

/**
 * A button that opens a native menu of [items]
 * (Material 3 dropdown menu on Android, UIMenu on iOS).
 *
 * The button shows the icon when one is given for the platform, otherwise [title].
 *
 * @param items The menu entries.
 * @param onItemClick Called with the entry the user picked.
 * @param modifier The [Modifier] applied to the menu button.
 * @param title Text of the menu button when no icon is given.
 * @param iosIcon SF Symbol name for the menu button on iOS, e.g. `"ellipsis.circle"`.
 * @param androidIcon Drawable resource for the menu button on Android.
 * @param colors The menu colors. See [KMenuDefaults.colors].
 */
@Composable
public expect fun KMenu(
    items: List<KMenuItem>,
    onItemClick: (KMenuItem) -> Unit,
    modifier: Modifier = Modifier,
    title: String = "",
    iosIcon: String? = null,
    androidIcon: DrawableResource? = null,
    colors: KMenuColors = KMenuDefaults.colors()
)
