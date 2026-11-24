package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Default values used by [KPicker].
 *
 * Every default is unspecified: the platform draws its native look (Material theme on Android,
 * system appearance on iOS). Pass a value only to override it.
 */
public object KPickerDefaults {
    /** Returns [KPickerColors]. Unspecified colors use the platform default. `disabledContentColor` is used for the rows that are not selected. */
    @Composable
    public fun colors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        disabledContainerColor: Color = Color.Unspecified,
        disabledContentColor: Color = Color.Unspecified
    ): KPickerColors = KPickerColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )
}
