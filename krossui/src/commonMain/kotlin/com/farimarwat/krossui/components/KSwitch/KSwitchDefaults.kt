package com.farimarwat.krossui.components.KSwitch

import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

/**
 * Default values and factory for creating [KSwitchColors].
 */
object KSwitchDefaults {

    /**
     * Creates a [KSwitchColors] instance with optional custom or default values.
     *
     * @param thumbColor Color of the thumb (toggle knob), default is [Color.White].
     * @param trackColorChecked Track color when checked, default is [PlatformColors.systemBlue].
     * @param trackColorUnchecked Track color when unchecked, default is [Color.Gray].
     * @return A [KSwitchColors] instance with the specified color configuration.
     */
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