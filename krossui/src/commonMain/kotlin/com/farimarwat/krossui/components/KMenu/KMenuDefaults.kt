package com.farimarwat.krossui.components.KMenu

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

object KMenuDefaults {
    @Composable
    fun colors(
        backgroundColor: Color = PlatformColors.systemGray,
        contentColor: Color = PlatformColors.label,
        disabledContentColor: Color = contentColor.copy(alpha = 0.4f)
    ): KMenuColors = KMenuColors(
        backgroundColor = backgroundColor,
        contentColor = contentColor,
        disabledContentColor = disabledContentColor
    )
}
