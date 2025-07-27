package com.farimarwat.krossui.components.KIconButton

import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

/**
 * Default values and helpers for [KIconButton] styling.
 */
object KIconButtonDefaults {

    /**
     * Returns the default [KIconButtonColors] for the button.
     *
     * @return A [KIconButtonColors] instance with default styling.
     */
    fun colors(): KIconButtonColors = KIconButtonColors(
        containerColor = PlatformColors.systemBlue,
        contentColor = Color.White,
        disabledContainerColor = Color.LightGray,
        disabledContentColor = Color.Gray
    )

    /**
     * Default font size for the button's text.
     */
    val FontSize = 14.sp

    /**
     * Default corner radius for the button.
     */
    val CornerRadius = 8.0
}
