package com.farimarwat.krossui.components.KButton

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

object KButtonDefaults {

    /**
     * Returns a [KButtonColors] instance with the given customization options.
     *
     * @param containerColor The background color for the enabled button.
     * @param contentColor The text color for the enabled button.
     * @param disabledBackgroundColor The background color when the button is disabled.
     * @param disabledTextColor The text color when the button is disabled.
     */
    fun colors(
        containerColor: Color = PlatformColors.systemBlue,
        contentColor: Color = Color.White,
        disabledBackgroundColor: Color = containerColor.copy(alpha = 0.3f),
        disabledTextColor: Color = contentColor.copy(alpha = 0.3f)
    ): KButtonColors = KButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledBackgroundColor,
        disabledContentColor = disabledTextColor
    )

    /**
     * The default font size used in [KButton] text, expressed in sp.
     */
    val DefaultFontSize: TextUnit = 14.sp
    /**
     * The default corner radius for [KButton], expressed in dp.
     */
    val CornerRadius = 8.0
}
