package com.farimarwat.krossui.components.KTextField

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

object KTextFieldDefaults {
    @Composable
    fun colors(
        textColor: Color = Color.Black,
        unfocusedBackgroundColor: Color = Color.White,
        focusedBackgroundColor:Color = Color.White,
        placeholderColor: Color = Color.Gray,
        unfocusedBorderColor: Color = Color.LightGray,
        focusedBorderColor: Color = PlatformColors.systemBlue,
        disabledTextColor: Color = Color.Gray,
        disabledBorderColor: Color = Color.Gray,
        readOnlyTextColor: Color = Color.DarkGray
    ): KTextFieldColors {
        return KTextFieldColors(
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
    }

    val BorderWidth = 1
    val CornerRadius = 8.0
    val Padding = 12
}