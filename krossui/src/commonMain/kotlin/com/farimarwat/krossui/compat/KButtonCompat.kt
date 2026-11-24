@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KButton

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farimarwat.krossui.PlatformColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KButtonColors")
)
public typealias KButtonColors = com.farimarwat.krossui.KButtonColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KButtonDefaults")
)
public object KButtonDefaults {
    public fun colors(
        containerColor: Color = PlatformColors.systemBlue,
        contentColor: Color = Color.White,
        disabledBackgroundColor: Color = containerColor.copy(alpha = 0.3f),
        disabledTextColor: Color = contentColor.copy(alpha = 0.3f)
    ): KButtonColors = KButtonColors(containerColor, contentColor, disabledBackgroundColor, disabledTextColor)

    public val DefaultFontSize: TextUnit = 14.sp
    public val CornerRadius: Double = 8.0
}

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KButton(text = text, onClick = onClick, modifier = modifier, enabled = isEnabled, colors = colors, fontSize = fontSize, cornerRadius = cornerRadius.dp)",
        "androidx.compose.ui.unit.dp"
    )
)
@Composable
public fun KButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    colors: KButtonColors = KButtonDefaults.colors(),
    fontSize: TextUnit = KButtonDefaults.DefaultFontSize,
    cornerRadius: Double = KButtonDefaults.CornerRadius,
    isEnabled: Boolean = true
): Unit = com.farimarwat.krossui.KButton(
    text = text,
    onClick = onClick,
    modifier = modifier,
    enabled = isEnabled,
    colors = colors,
    fontSize = fontSize,
    cornerRadius = cornerRadius.dp
)
