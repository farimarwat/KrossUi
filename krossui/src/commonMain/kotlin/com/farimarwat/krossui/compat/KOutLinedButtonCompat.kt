@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KOutLinedButton

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farimarwat.krossui.KOutlinedButtonColors
import com.farimarwat.krossui.PlatformColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KOutlinedButtonColors")
)
public typealias KOutLinedButtonColors = KOutlinedButtonColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KOutlinedButtonDefaults")
)
public object KOutLinedButtonDefaults {
    public fun colors(
        containerColor: Color = PlatformColors.systemBackground,
        contentColor: Color = PlatformColors.systemBlue,
        disabledBackgroundColor: Color = containerColor.copy(alpha = 0.3f),
        disabledTextColor: Color = contentColor.copy(alpha = 0.3f),
        borderColor: Color = PlatformColors.systemBlue,
        disabledBorderColor: Color = borderColor.copy(alpha = 0.3f)
    ): KOutlinedButtonColors = KOutlinedButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledBackgroundColor,
        disabledContentColor = disabledTextColor,
        borderColor = borderColor,
        disabledBorderColor = disabledBorderColor
    )

    public val DefaultFontSize: TextUnit = 14.sp
    public val CornerRadius: Double = 8.0
    public val BorderWidth: Double = 1.0
}

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KOutlinedButton(text = text, onClick = onClick, modifier = modifier, enabled = isEnabled, colors = colors, fontSize = fontSize, cornerRadius = cornerRadius.dp, borderWidth = borderWidth.dp)",
        "androidx.compose.ui.unit.dp"
    )
)
@Composable
public fun KOutLinedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    colors: KOutlinedButtonColors = KOutLinedButtonDefaults.colors(),
    fontSize: TextUnit = KOutLinedButtonDefaults.DefaultFontSize,
    cornerRadius: Double = KOutLinedButtonDefaults.CornerRadius,
    borderWidth: Double = KOutLinedButtonDefaults.BorderWidth,
    isEnabled: Boolean = true
): Unit = com.farimarwat.krossui.KOutlinedButton(
    text = text,
    onClick = onClick,
    modifier = modifier,
    enabled = isEnabled,
    colors = colors,
    fontSize = fontSize,
    cornerRadius = cornerRadius.dp,
    borderWidth = borderWidth.dp
)
