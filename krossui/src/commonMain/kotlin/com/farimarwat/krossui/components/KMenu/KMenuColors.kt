package com.farimarwat.krossui.components.KMenu

import androidx.compose.ui.graphics.Color

/**
 * Defines color properties used by the [KMenu] component.
 *
 * @property backgroundColor Background color of the menu surface.
 * @property contentColor Color used for menu item text and icons.
 * @property disabledContentColor Color used for disabled items.
 */
data class KMenuColors(
    val backgroundColor: Color,
    val contentColor: Color,
    val disabledContentColor: Color
)

