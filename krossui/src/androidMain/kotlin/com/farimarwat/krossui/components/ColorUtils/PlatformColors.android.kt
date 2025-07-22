package com.farimarwat.krossui.components.ColorUtils

import androidx.compose.ui.graphics.Color


actual object PlatformColors {
    actual val systemBlue: Color = Color.Blue
    actual val systemRed: Color = Color.Red
    actual val systemGreen: Color = Color.Green
    actual val systemOrange: Color = Color(0xFFFB8C00)     // Material Orange 600
    actual val systemPurple: Color = Color(0xFF8E24AA)     // Material Purple 600
    actual val systemGray: Color = Color(0xFF9E9E9E)       // Material Gray 500

    actual val label: Color = Color(0xDE000000)            // 87% Black for primary text
    actual val secondaryLabel: Color = Color(0x99000000)   // 60% Black for secondary text
    actual val systemBackground: Color = Color(0xFFFFFFFF) // White background
}
