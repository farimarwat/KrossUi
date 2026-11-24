package com.farimarwat.krossui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/** Default values used by [KOutlinedButton]. */
public object KOutlinedButtonDefaults {

    /** Returns [KOutlinedButtonColors]; disabled colors default to the enabled ones at 30% alpha. */
    public fun colors(
        containerColor: Color = PlatformColors.systemBackground,
        contentColor: Color = PlatformColors.systemBlue,
        disabledContainerColor: Color = containerColor.copy(alpha = 0.3f),
        disabledContentColor: Color = contentColor.copy(alpha = 0.3f),
        borderColor: Color = PlatformColors.systemBlue,
        disabledBorderColor: Color = borderColor.copy(alpha = 0.3f)
    ): KOutlinedButtonColors = KOutlinedButtonColors(
        containerColor = containerColor,
        contentColor = contentColor,
        disabledContainerColor = disabledContainerColor,
        disabledContentColor = disabledContentColor,
        borderColor = borderColor,
        disabledBorderColor = disabledBorderColor
    )

    /** Default label size. */
    public val FontSize: TextUnit = 14.sp

    /** Default corner radius. */
    public val CornerRadius: Dp = 8.dp

    /** Default border width. */
    public val BorderWidth: Dp = 1.dp
}
