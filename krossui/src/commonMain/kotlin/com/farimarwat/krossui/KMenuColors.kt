package com.farimarwat.krossui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Defines color properties used by the [KMenu] component.
 *
 * @property containerColor Background color of the menu surface.
 * @property contentColor Color used for menu item text and icons.
 * @property disabledContentColor Color used for disabled items.
 */
@Immutable
public data class KMenuColors(val containerColor: Color, val contentColor: Color, val disabledContentColor: Color)
