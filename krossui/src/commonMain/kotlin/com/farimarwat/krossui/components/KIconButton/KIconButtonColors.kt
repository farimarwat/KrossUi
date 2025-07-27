package com.farimarwat.krossui.components.KIconButton

import androidx.compose.ui.graphics.Color

/**
 * A data class that defines the color scheme used by [KIconButton].
 *
 * @param containerColor The background color of the button when enabled.
 * @param contentColor The color of the text and icon when enabled.
 * @param disabledContainerColor The background color when the button is disabled.
 * @param disabledContentColor The color of the text and icon when the button is disabled.
 */
data class KIconButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
)
