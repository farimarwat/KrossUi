package com.farimarwat.krossui.components.KButton

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

object KButtonDefaults {

    fun colors(
        containerColor: Color = PlatformColors.systemBlue,
        contentColor: Color = Color.White,
        disabledBackgroundColor: Color = containerColor.copy(alpha = 0.3f),
        disabledTextColor: Color = contentColor.copy(alpha = 0.3f)
    ): KButtonColors = KButtonColors(
        backgroundColor = containerColor,
        textColor = contentColor,
        disabledBackgroundColor = disabledBackgroundColor,
        disabledTextColor = disabledTextColor
    )

    val DefaultFontSize: TextUnit = 14.sp
    val CornerRadius = 8.0
}
