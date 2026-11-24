package com.farimarwat.krossui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Default values used by [KButton]. */
public object KButtonDefaults {

    /** Returns [KButtonColors]; disabled colors default to the enabled ones at 30% alpha. */
    public fun colors(
        containerColor: Color = PlatformColors.systemBlue,
        contentColor: Color = Color.White,
        disabledContainerColor: Color = containerColor.copy(alpha = 0.3f),
        disabledContentColor: Color = contentColor.copy(alpha = 0.3f)
    ): KButtonColors = KButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    /** Default label size. */
    public val FontSize: TextUnit = 14.sp

    /** Default corner radius. */
    public val CornerRadius: Dp = 8.dp
}
