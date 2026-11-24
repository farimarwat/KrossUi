package com.farimarwat.krossui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Holds the color configuration for the KSwitch component.
 *
 * @param thumbColor Color of the thumb (toggle knob).
 * @param trackColorChecked Track color when the switch is in the checked state.
 * @param trackColorUnchecked Track color when the switch is in the unchecked state.
 */
@Immutable
public data class KSwitchColors(val thumbColor: Color, val trackColorChecked: Color, val trackColorUnchecked: Color)
