package com.farimarwat.krossui.components.KPicker

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

object KPickerDefaults {

    @Composable
    fun colors(): KPickerColors = KPickerColors(
        containerColor = Color.White,
        contentColor = PlatformColors.systemBlue,
        disabledContainerColor = PlatformColors.systemGray.copy(alpha = 0.5f),
        disabledContentColor = PlatformColors.systemGray
    )
}