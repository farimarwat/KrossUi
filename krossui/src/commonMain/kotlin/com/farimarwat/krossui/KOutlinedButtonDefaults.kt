package com.farimarwat.krossui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

/**
 * Default values used by [KOutlinedButton].
 *
 * Every default is unspecified: the platform draws its native look (Material theme on Android,
 * system appearance on iOS). Pass a value only to override it.
 */
public object KOutlinedButtonDefaults {
    /** Returns [KOutlinedButtonColors]. Unspecified colors use the platform default. */
    public fun colors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        disabledContainerColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified,
        borderColor: Color = Color.Unspecified,
        disabledBorderColor: Color = Color.Unspecified
    ): KOutlinedButtonColors = KOutlinedButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        borderColor = borderColor,
        disabledBorderColor = disabledBorderColor
    )

    /** Label size; unspecified = platform default. */
    public val FontSize: TextUnit = TextUnit.Unspecified

    /** Corner radius; unspecified = platform default shape. */
    public val CornerRadius: Dp = Dp.Unspecified

    /** Border width; unspecified = platform default. */
    public val BorderWidth: Dp = Dp.Unspecified
}
