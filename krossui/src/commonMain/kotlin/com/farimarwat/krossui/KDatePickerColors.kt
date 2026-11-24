package com.farimarwat.krossui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Holds the color configuration for various parts of the `KDatePicker`.
 *
 * @property containerColor Background color of the dialog container.
 * @property contentColor Color used for main content like date text and selectors.
 * @property footerContainerColor Background color of the footer section.
 * @property footerContentColor Color used for footer buttons/text (e.g., "OK", "Cancel").
 */
@Immutable
public data class KDatePickerColors(
    val containerColor: Color,
    val contentColor: Color,
    val footerContainerColor: Color,
    val footerContentColor: Color
)
