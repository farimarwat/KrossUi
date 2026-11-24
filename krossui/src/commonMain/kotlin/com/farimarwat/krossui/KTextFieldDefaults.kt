package com.farimarwat.krossui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Provides default configuration values for [KTextField], including colors, border width, and padding.
 */
public object KTextFieldDefaults {

    /**
     * Returns a [KTextFieldColors] instance with color values for all supported states.
     *
     * @param textColor Color of the input text when enabled.
     * @param unfocusedBackgroundColor Background color when not focused.
     * @param focusedBackgroundColor Background color when focused.
     * @param placeholderColor Color of the placeholder text.
     * @param unfocusedBorderColor Border color when not focused.
     * @param focusedBorderColor Border color when focused.
     * @param disabledTextColor Text color when disabled.
     * @param disabledBorderColor Border color when disabled.
     * @param readOnlyTextColor Text color when the field is read-only.
     */
    @Composable
    public fun colors(
        textColor: Color = Color.Black,
        unfocusedBackgroundColor: Color = Color.White,
        focusedBackgroundColor: Color = Color.White,
        placeholderColor: Color = Color.Gray,
        unfocusedBorderColor: Color = Color.LightGray,
        focusedBorderColor: Color = PlatformColors.systemBlue,
        disabledTextColor: Color = Color.Gray,
        disabledBorderColor: Color = Color.Gray,
        readOnlyTextColor: Color = Color.DarkGray
    ): KTextFieldColors = KTextFieldColors(
        textColor = textColor,
        unfocusedBackgroundColor = unfocusedBackgroundColor,
        focusedBackgroundColor = focusedBackgroundColor,
        placeholderColor = placeholderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        focusedBorderColor = focusedBorderColor,
        disabledTextColor = disabledTextColor,
        disabledBorderColor = disabledBorderColor,
        readOnlyTextColor = readOnlyTextColor
    )

    /** Default border width. */
    public val BorderWidth: Dp = 1.dp

    /** Default corner radius. */
    public val CornerRadius: Dp = 8.dp

    /** Default padding between the border and the text. */
    public val ContentPadding: PaddingValues = PaddingValues(12.dp)
}
