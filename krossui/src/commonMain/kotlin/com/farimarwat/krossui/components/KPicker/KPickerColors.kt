package com.farimarwat.krossui.components.KPicker

import androidx.compose.ui.graphics.Color

/**
 * Holds color definitions for different states of the [KPicker] component.
 *
 * @property containerColor The background color when the picker is enabled.
 * @property contentColor The text color when the picker is enabled.
 * @property disabledContainerColor The background color when the picker is disabled.
 * @property disabledContentColor The text color when the picker is disabled.
 */
data class KPickerColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
)