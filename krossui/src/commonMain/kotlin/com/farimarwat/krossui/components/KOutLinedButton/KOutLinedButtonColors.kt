package com.farimarwat.krossui.components.KOutLinedButton

import androidx.compose.ui.graphics.Color

/**
 * Represents the color configuration for a [KOutLinedButton], including text, background, and border
 * for both enabled and disabled states.
 *
 * @property backgroundColor The background color when the button is enabled.
 * @property textColor The text color when the button is enabled.
 * @property disabledBackgroundColor The background color when the button is disabled.
 * @property disabledTextColor The text color when the button is disabled.
 * @property borderColor The border color when the button is enabled.
 * @property disabledBorderColor The border color when the button is disabled.
 */
data class KOutLinedButtonColors(
    val backgroundColor: Color,
    val textColor: Color,
    val disabledBackgroundColor: Color,
    val disabledTextColor: Color,
    val borderColor: Color,
    val disabledBorderColor: Color
)
