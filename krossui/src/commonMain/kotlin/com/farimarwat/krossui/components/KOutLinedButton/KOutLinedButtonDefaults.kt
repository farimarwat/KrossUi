package com.farimarwat.krossui.components.KOutLinedButton

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.farimarwat.krossui.components.ColorUtils.PlatformColors
/**
 * Provides default configurations for [KOutLinedButton], including colors, font size, corner radius, and border width.
 */
object KOutLinedButtonDefaults {
    /**
     * Returns a [KOutLinedButtonColors] instance with customizable values for container, text, and border colors.
     *
     * @param containerColor The background color of the button when enabled.
     * @param contentColor The text color of the button when enabled.
     * @param disabledBackgroundColor The background color of the button when disabled.
     * @param disabledTextColor The text color of the button when disabled.
     * @param borderColor The border color when the button is enabled.
     * @param disabledBorderColor The border color when the button is disabled.
     */
    fun colors(
        containerColor: Color = PlatformColors.systemBackground,
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
    /**
     * The default font size for the button text.
     */
    val DefaultFontSize: TextUnit = 14.sp
    /**
     * The default corner radius for the button in dp.
     */
    val CornerRadius = 8.0
    /**
     * The default border width for the outlined button in dp.
     */
    val BorderWidth: Double = 1.0
}