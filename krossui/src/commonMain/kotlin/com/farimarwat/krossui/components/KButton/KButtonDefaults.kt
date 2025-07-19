package com.farimarwat.krossui.components.KButton

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

object KButtonDefaults {

    fun colors(
        containerColor: Color = Color.Blue,
        contentColor: Color = Color.White,
        disabledBackgroundColor: Color = containerColor.copy(alpha = 0.3f),
        disabledTextColor: Color = contentColor.copy(alpha = 0.3f)
    ): KButtonColors = KButtonColors(
        backgroundColor = containerColor,
        textColor = contentColor,
        disabledBackgroundColor = disabledBackgroundColor,
        disabledTextColor = disabledTextColor
    )

    val DefaultFontSize = 14.sp
    val CornerRadius = 8.0
}
