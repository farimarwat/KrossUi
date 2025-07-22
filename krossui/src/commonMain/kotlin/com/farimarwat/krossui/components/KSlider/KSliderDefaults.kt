package com.farimarwat.krossui.components.KSlider

import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

// commonMain
object KSliderDefaults {

    fun colors(
        activeTrackColor: Color = PlatformColors.systemBlue,
        inactiveTrackColor: Color = PlatformColors.systemGray,
        thumbColor: Color = Color.White,
        disabledActiveTrackColor: Color = activeTrackColor.copy(alpha = 0.3f),
        disabledInactiveTrackColor: Color = inactiveTrackColor.copy(alpha = 0.3f),
        disabledThumbColor: Color = thumbColor.copy(alpha = 0.3f)
    ): KSliderColors = KSliderColors(
        activeTrackColor = activeTrackColor,
        inactiveTrackColor = inactiveTrackColor,
        thumbColor = thumbColor,
        disabledActiveTrackColor = disabledActiveTrackColor,
        disabledInactiveTrackColor = disabledInactiveTrackColor,
        disabledThumbColor = disabledThumbColor
    )
}
