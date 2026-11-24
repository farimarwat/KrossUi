package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * Default values used by [KTextField].
 *
 * Every default is unspecified: the platform draws its native look (Material theme on Android,
 * system appearance on iOS). Pass a value only to override it.
 */
public object KTextFieldDefaults {
    /** Returns [KTextFieldColors]. Unspecified colors use the platform default. */
    @Composable
    public fun colors(
        textColor: Color = Color.Unspecified,
        unfocusedBackgroundColor: Color = Color.Unspecified,
        focusedBackgroundColor: Color = Color.Unspecified,
        placeholderColor: Color = Color.Unspecified,
        unfocusedBorderColor: Color = Color.Unspecified,
        focusedBorderColor: Color = Color.Unspecified,
        disabledTextColor: Color = Color.Unspecified,
        disabledBorderColor: Color = Color.Unspecified,
        readOnlyTextColor: Color = Color.Unspecified
    ): KTextFieldColors = KTextFieldColors(
        textColor = textColor,
        unfocusedBackgroundColor = unfocusedBackgroundColor,
        focusedBackgroundColor = focusedBackgroundColor,
        placeholderColor = placeholderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        focusedBorderColor = focusedBorderColor,
        disabledTextColor = disabledTextColor,
        disabledBorderColor = disabledBorderColor,
        readOnlyTextColor = readOnlyTextColor
    )

    /** Border width; unspecified = platform default. */
    public val BorderWidth: Dp = Dp.Unspecified

    /** Corner radius; unspecified = platform default shape. */
    public val CornerRadius: Dp = Dp.Unspecified
}
