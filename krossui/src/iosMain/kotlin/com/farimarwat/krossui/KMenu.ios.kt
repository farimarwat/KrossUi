package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import com.farimarwat.krossui.utils.MinTouchTarget
import com.farimarwat.krossui.utils.NativeView
import com.farimarwat.krossui.utils.toUiColorOrNull
import org.jetbrains.compose.resources.DrawableResource
import platform.UIKit.UIAction
import platform.UIKit.UIButton
import platform.UIKit.UIButtonTypeSystem
import platform.UIKit.UIControlStateNormal
import platform.UIKit.UIImage
import platform.UIKit.UIMenu
import platform.UIKit.UIMenuElementAttributesDisabled

@Composable
public actual fun KMenu(
    items: List<KMenuItem>,
    onItemClick: (KMenuItem) -> Unit,
    modifier: Modifier,
    title: String,
    iosIcon: String?,
    androidIcon: DrawableResource?, // Ignored on iOS
    contentDescription: String?,
    colors: KMenuColors
) {
    val currentOnItemClick by rememberUpdatedState(onItemClick)

    // A system button that opens a native UIMenu on tap
    NativeView(
        factory = {
            UIButton.buttonWithType(UIButtonTypeSystem).apply { showsMenuAsPrimaryAction = true }
        },
        update = { button ->
            val icon = iosIcon?.takeIf { it.isNotBlank() }?.let { UIImage.systemImageNamed(it) }
            button.setImage(icon, forState = UIControlStateNormal)
            button.setTitle(if (icon == null) title else null, forState = UIControlStateNormal)
            button.setTitleColor(colors.contentColor.toUiColorOrNull(), forState = UIControlStateNormal)
            button.menu = UIMenu.menuWithTitle(
                title = "",
                children = items.map { item ->
                    UIAction.actionWithTitle(
                        title = item.title,
                        image = item.iosIcon?.let { UIImage.systemImageNamed(it) },
                        identifier = null,
                        handler = { _ -> currentOnItemClick(item) }
                    ).apply {
                        if (!item.enabled) attributes = UIMenuElementAttributesDisabled
                    }
                }
            )
        },
        modifier = modifier,
        minSize = DpSize(MinTouchTarget, MinTouchTarget),
        accessibilityLabel = contentDescription ?: title.ifBlank { null }
    )
}
