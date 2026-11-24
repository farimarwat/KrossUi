package com.farimarwat.krossui.components.ColorUtils

import androidx.compose.ui.graphics.Color


public actual object PlatformColors {
    public actual val systemBlue: Color = Color(0xFF007AFF)   // iOS systemBlue
    public actual val systemRed: Color = Color(0xFFFF3B30)    // iOS systemRed
    public actual val systemGreen: Color = Color(0xFF34C759)  // iOS systemGreen
    public actual val systemOrange: Color = Color(0xFFFF9500) // iOS systemOrange
    public actual val systemPurple: Color = Color(0xFFAF52DE) // iOS systemPurple
    public actual val systemGray: Color = Color(0xFF8E8E93)    // iOS systemGray

    public actual val label: Color = Color(0xFF000000)         // Approx for labelColor (black in light mode)
    public actual val secondaryLabel: Color = Color(0x993C3C43) // secondaryLabelColor: #3C3C43 at 60% alpha (ARGB)
    public actual val systemBackground: Color = Color(0xFFFFFFFF) // White background for light mode
}

