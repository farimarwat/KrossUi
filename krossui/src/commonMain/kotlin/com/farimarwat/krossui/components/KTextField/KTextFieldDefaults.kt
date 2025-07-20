package com.farimarwat.krossui.components.KTextField

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color

object KTextFieldDefaults {
    @Composable
    fun colors(
        textColor: Color = Color.Black,
        unfocusedBackgroundColor: Color = Color.White,
        focusedBackgroundColor:Color = Color.LightGray,
        placeholderColor: Color = Color.Gray,
        borderColor: Color = Color.LightGray,
        focusedBorderColor: Color = Color.Blue,
        disabledTextColor: Color = Color.Gray,
        disabledBorderColor: Color = Color.Gray,
        readOnlyTextColor: Color = Color.DarkGray
    ): KTextFieldColors {
        return KTextFieldColors(
            textColor = textColor,
            unfocusedBackgroundColor = unfocusedBackgroundColor,
            focusedBackgroundColor = focusedBackgroundColor,
            placeholderColor = placeholderColor,
            borderColor = borderColor,
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