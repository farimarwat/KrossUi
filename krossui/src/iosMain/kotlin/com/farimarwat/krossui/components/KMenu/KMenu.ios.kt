package com.farimarwat.krossui.components.KMenu

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import org.jetbrains.compose.resources.DrawableResource
import platform.CoreGraphics.CGRectMake
import platform.UIKit.*

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun KMenu(
    title: String,
    iOSIcon: String?,
    androidIcon: DrawableResource?, // not used in iOS
    items: List<KMenuItem>,
    onItemClick: (KMenuItem) -> Unit,
    modifier: Modifier,
    colors: KMenuColors
) {
    val itemClickCallback = remember { mutableStateOf(onItemClick) }
    itemClickCallback.value = onItemClick
    val button = remember {
        UIButton.buttonWithType(UIButtonTypeSystem).apply {
            // Set icon or text
            if (iOSIcon != null) {
                val iconImage = UIImage.systemImageNamed(iOSIcon)
                setImage(iconImage, forState = UIControlStateNormal)
                setTitle("", forState = UIControlStateNormal)
            } else {
                setTitle(title, forState = UIControlStateNormal)
                setTitleColor(colors.contentColor.toUiColor(), UIControlStateNormal)
            }
        }
    }
    val size = button.intrinsicContentSize
    UIKitView(
        modifier = Modifier
            .width(size.useContents { width }.dp)
            .height(size.useContents { height }.dp)
            .then(modifier),
        factory = {
            val actions = items.map { item ->
                UIAction.actionWithTitle(
                    title = item.title,
                    image = item.iosIcon?.let { UIImage.systemImageNamed(it) },
                    identifier = null,
                    handler = { _ -> itemClickCallback.value(item) }
                ).apply {
                    if (!item.enabled) {
                        this.attributes = UIMenuElementAttributesDisabled
                    }
                }
            }

            // Create menu
            val menu = UIMenu.menuWithTitle("", children = actions)
            button.menu = menu
            button.showsMenuAsPrimaryAction = true



            button
        },
        update = {
            // Recreate menu when items update
            val updatedActions = items.map { item ->
                UIAction.actionWithTitle(
                    title = item.title,
                    image = item.iosIcon?.let { UIImage.systemImageNamed(it) },
                    identifier = null,
                    handler = { _ -> itemClickCallback.value(item) }
                ).apply {
                    if (!item.enabled) {
                        this.attributes = UIMenuElementAttributesDisabled
                    }
                }
            }
            val updatedMenu = UIMenu.menuWithTitle("", children = updatedActions)
            it.menu = updatedMenu

            // Update colors
            it.setTitleColor(colors.contentColor.toUiColor(), UIControlStateNormal)
        }
    )
}
