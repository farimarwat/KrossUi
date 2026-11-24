@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KIconButton

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farimarwat.krossui.PlatformColors
import org.jetbrains.compose.resources.DrawableResource

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KIconButtonColors")
)
public typealias KIconButtonColors = com.farimarwat.krossui.KIconButtonColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KIconButtonDefaults")
)
public object KIconButtonDefaults {
    public fun colors(): KIconButtonColors = KIconButtonColors(
        containerColor = PlatformColors.systemBlue,
        contentColor = Color.White,
        disabledContainerColor = Color.LightGray,
        disabledContentColor = Color.Gray
    )

    public val FontSize: TextUnit = 14.sp
    public val CornerRadius: Double = 8.0
}

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KIconButton(onClick = onClick, modifier = modifier, enabled = isEnabled, title = title, iosIcon = iosIcon, androidIcon = androidIcon, colors = colors, fontSize = fontSize, cornerRadius = cornerRadius.dp)",
        "androidx.compose.ui.unit.dp"
    )
)
@Composable
public fun KIconButton(
    modifier: Modifier = Modifier,
    title: String? = null,
    onClick: () -> Unit,
    colors: KIconButtonColors = KIconButtonDefaults.colors(),
    fontSize: TextUnit = KIconButtonDefaults.FontSize,
    cornerRadius: Double = KIconButtonDefaults.CornerRadius,
    isEnabled: Boolean = true,
    iosIcon: String? = null,
    androidIcon: DrawableResource? = null
): Unit = com.farimarwat.krossui.KIconButton(
    onClick = onClick,
    modifier = modifier,
    enabled = isEnabled,
    title = title,
    iosIcon = iosIcon,
    androidIcon = androidIcon,
    colors = colors,
    fontSize = fontSize,
    cornerRadius = cornerRadius.dp
)
