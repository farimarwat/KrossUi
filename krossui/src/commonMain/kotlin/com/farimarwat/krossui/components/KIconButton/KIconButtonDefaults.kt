package com.farimarwat.krossui.components.KIconButton

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

object KIconButtonDefaults {
    fun  colors(): KIconButtonColors = KIconButtonColors(
            containerColor = PlatformColors.systemBlue,
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.Gray
        )

    val FontSize = 14.sp
    val CornerRadius = 8.0
}