package com.farimarwat.krossui.components.KDatePicker

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

/**
 * Provides default color configurations for `KDatePicker`.
 */
object KDatePickerDefaults {

    /**
     * Returns a [KDatePickerColors] instance with customizable defaults.
     *
     * @param containerColor Background color of the dialog.
     * @param contentColor Main content color, such as the selected date.
     * @param footerContainerColor Background color of the footer area.
     * @param footerContentColor Color used for footer buttons and labels.
     */
    @Composable
    fun colors(
        containerColor: Color = PlatformColors.systemBackground,
        contentColor: Color = PlatformColors.systemBlue,
        footerContainerColor: Color = PlatformColors.systemGray.copy(alpha = 0.5f),
        footerContentColor: Color = PlatformColors.systemBlue
    ): KDatePickerColors = KDatePickerColors(
        containerColor = containerColor,
        contentColor = contentColor,
        footerContainerColor = footerContainerColor,
        footerContentColor = footerContentColor
    )
}