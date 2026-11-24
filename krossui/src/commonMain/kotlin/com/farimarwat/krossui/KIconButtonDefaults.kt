package com.farimarwat.krossui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Default values used by [KIconButton]. */
public object KIconButtonDefaults {

    /** Returns [KIconButtonColors]. */
    public fun colors(
        containerColor: Color = PlatformColors.systemBlue,
        contentColor: Color = Color.White,
        disabledContainerColor: Color = Color.LightGray,
        disabledContentColor: Color = Color.Gray
    ): KIconButtonColors = KIconButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor
    )

    /** Default title size. */
    public val FontSize: TextUnit = 14.sp

    /** Default corner radius. */
    public val CornerRadius: Dp = 8.dp
}
