package com.farimarwat.krossui.components.KSwitch

import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

object KSwitchDefaults {
    fun colors(
        thumbColor: Color = Color.White,
        trackColorChecked: Color = PlatformColors.systemBlue,
        trackColorUnchecked: Color = Color.Gray,
    ): KSwitchColors = KSwitchColors(
        thumbColor = thumbColor,
        trackColorChecked = trackColorChecked,
        trackColorUnchecked = trackColorUnchecked
    )
}