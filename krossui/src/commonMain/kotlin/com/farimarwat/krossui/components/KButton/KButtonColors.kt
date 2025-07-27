package com.farimarwat.krossui.components.KButton

import androidx.compose.ui.graphics.Color

/**
 * Represents the color configuration for a [KButton] in both enabled and disabled states.
 *
 * @property containerColor The background color of the enabled button.
 * @property contentColor The text color of the enabled button.
 * @property disabledContainerColor The background color of the disabled button.
 * @property disabledContentColor The text color of the disabled button.
 */
data class KButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
)
