package com.farimarwat.krossui

import androidx.compose.ui.graphics.Color

public actual object PlatformColors {
    public actual val systemBlue: Color = Color.Blue
    public actual val systemRed: Color = Color.Red
    public actual val systemGreen: Color = Color.Green
    public actual val systemOrange: Color = Color(0xFFFB8C00) // Material Orange 600
    public actual val systemPurple: Color = Color(0xFF8E24AA) // Material Purple 600
    public actual val systemGray: Color = Color(0xFF9E9E9E) // Material Gray 500

    public actual val label: Color = Color(0xDE000000) // 87% Black for primary text
    public actual val secondaryLabel: Color = Color(0x99000000) // 60% Black for secondary text
    public actual val systemBackground: Color = Color(0xFFFFFFFF) // White background
}
