package com.farimarwat.krossui.components.KSwitch

import androidx.compose.ui.graphics.Color

/**
 * Holds the color configuration for the KSwitch component.
 *
 * @param thumbColor Color of the thumb (toggle knob).
 * @param trackColorChecked Track color when the switch is in the checked state.
 * @param trackColorUnchecked Track color when the switch is in the unchecked state.
 */
data class KSwitchColors(
    val thumbColor: Color,
    val trackColorChecked: Color,
    val trackColorUnchecked: Color,
)
