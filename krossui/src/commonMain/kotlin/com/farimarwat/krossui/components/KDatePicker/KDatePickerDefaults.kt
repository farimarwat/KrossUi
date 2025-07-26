package com.farimarwat.krossui.components.KDatePicker

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

object KDatePickerDefaults {

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