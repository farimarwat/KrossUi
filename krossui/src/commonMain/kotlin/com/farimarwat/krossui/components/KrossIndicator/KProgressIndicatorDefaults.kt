package com.farimarwat.krossui.components.KrossIndicator

import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

object KProgressIndicatorDefaults {
    fun colors(
        color: Color = PlatformColors.systemBlue
    ): KProgressIndicatorColors = KProgressIndicatorColors(color)

    val DefaultSize: Double = 36.0
}