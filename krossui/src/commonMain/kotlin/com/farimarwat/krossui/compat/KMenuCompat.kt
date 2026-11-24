@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KMenu

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.PlatformColors
import org.jetbrains.compose.resources.DrawableResource

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KMenuColors")
)
public typealias KMenuColors = com.farimarwat.krossui.KMenuColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KMenuItem")
)
public typealias KMenuItem = com.farimarwat.krossui.KMenuItem

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KMenuDefaults")
)
public object KMenuDefaults {
    @Composable
    public fun colors(
        backgroundColor: Color = PlatformColors.systemGray,
        contentColor: Color = PlatformColors.label,
        disabledContentColor: Color = contentColor.copy(alpha = 0.4f)
    ): KMenuColors = KMenuColors(
        containerColor = backgroundColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor
    )
}

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KMenu(items = items, onItemClick = onItemClick, modifier = modifier, title = title, iosIcon = iOSIcon, androidIcon = androidIcon, colors = colors)",
        "androidx.compose.ui.unit.dp"
    )
)
@Composable
public fun KMenu(
    title: String = "Hello",
    iOSIcon: String? = null,
    androidIcon: DrawableResource? = null,
    items: List<KMenuItem>,
    onItemClick: (KMenuItem) -> Unit,
    modifier: Modifier = Modifier,
    colors: KMenuColors = KMenuDefaults.colors()
): Unit = com.farimarwat.krossui.KMenu(
    items = items,
    onItemClick = onItemClick,
    modifier = modifier,
    title = title,
    iosIcon = iOSIcon,
    androidIcon = androidIcon,
    colors = colors
)
