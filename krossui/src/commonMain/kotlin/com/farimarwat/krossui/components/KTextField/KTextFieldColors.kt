package com.farimarwat.krossui.components.KTextField

import androidx.compose.ui.graphics.Color

/**
 * Defines color properties for various states of the [KTextField] such as focus, disabled, and read-only.
 *
 * @property textColor The color of the entered text when enabled.
 * @property unfocusedBackgroundColor Background when the field is not focused.
 * @property focusedBackgroundColor Background when the field is focused.
 * @property placeholderColor The color of the placeholder text.
 * @property unfocusedBorderColor Border color when the field is not focused.
 * @property focusedBorderColor Border color when the field is focused.
 * @property disabledTextColor Text color when the field is disabled.
 * @property disabledBorderColor Border color when the field is disabled.
 * @property readOnlyTextColor Text color when the field is read-only.
 */
data class KTextFieldColors(
    val textColor: Color,
    val unfocusedBackgroundColor: Color,
    val focusedBackgroundColor: Color,
    val placeholderColor: Color,
    val unfocusedBorderColor: Color,
    val focusedBorderColor: Color,
    val disabledTextColor: Color,
    val disabledBorderColor: Color,
    val readOnlyTextColor: Color
)