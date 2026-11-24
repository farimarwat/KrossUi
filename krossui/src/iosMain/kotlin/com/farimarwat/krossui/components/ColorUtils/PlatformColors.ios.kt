package com.farimarwat.krossui.components.ColorUtils

import androidx.compose.ui.graphics.Color


actual object PlatformColors {
    actual val systemBlue: Color = Color(0xFF007AFF)   // iOS systemBlue
    actual val systemRed: Color = Color(0xFFFF3B30)    // iOS systemRed
    actual val systemGreen: Color = Color(0xFF34C759)  // iOS systemGreen
    actual val systemOrange: Color = Color(0xFFFF9500) // iOS systemOrange
    actual val systemPurple: Color = Color(0xFFAF52DE) // iOS systemPurple
    actual val systemGray: Color = Color(0xFF8E8E93)    // iOS systemGray

    actual val label: Color = Color(0xFF000000)         // Approx for labelColor (black in light mode)
    actual val secondaryLabel: Color = Color(0x993C3C43) // secondaryLabelColor: #3C3C43 at 60% alpha (ARGB)
    actual val systemBackground: Color = Color(0xFFFFFFFF) // White background for light mode
}

