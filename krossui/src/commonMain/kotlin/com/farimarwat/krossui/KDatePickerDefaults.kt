package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * Default values used by [KDatePicker].
 *
 * Every default is unspecified: the platform draws its native look (Material theme on Android,
 * system appearance on iOS). Pass a value only to override it.
 */
public object KDatePickerDefaults {
    /** Returns [KDatePickerColors]. Unspecified colors use the platform default. */
    @Composable
    public fun colors(
        containerColor: Color = Color.Unspecified,
        contentColor: Color = Color.Unspecified,
        footerContainerColor: Color = Color.Unspecified,
        footerContentColor: Color = Color.Unspecified
    ): KDatePickerColors = KDatePickerColors(
        containerColor = containerColor,
        contentColor = contentColor,
        footerContainerColor = footerContainerColor,
        footerContentColor = footerContentColor
    )
}
