package com.farimarwat.krossui.components.KOutLinedButton

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

object KOutLinedButtonDefaults {

    fun colors(
        containerColor: Color = Color.White,
        contentColor: Color = PlatformColors.systemBlue,
        disabledBackgroundColor: Color = containerColor.copy(alpha = 0.3f),
        disabledTextColor: Color = contentColor.copy(alpha = 0.3f),
        borderColor:Color = PlatformColors.systemBlue,
        disabledBorderColor: Color = borderColor.copy(alpha = 0.3f)
    ): KOutLinedButtonColors = KOutLinedButtonColors(
        backgroundColor = containerColor,
        textColor = contentColor,
        disabledBackgroundColor = disabledBackgroundColor,
        disabledTextColor = disabledTextColor,
        borderColor = borderColor,
        disabledBorderColor = disabledBorderColor
    )

    val DefaultFontSize: TextUnit = 14.sp
    val CornerRadius = 8.0
    val BorderWidth: Double = 1.0
}