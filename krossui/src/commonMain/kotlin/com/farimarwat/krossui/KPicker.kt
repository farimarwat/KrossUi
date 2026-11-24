package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A scrolling wheel picker for a list of strings
 * (UIPickerView on iOS, a wheel-style list on Android).
 *
 * @param items The values to choose from.
 * @param onItemPicked Called with the value the user selected.
 * @param modifier The [Modifier] applied to the picker.
 * @param colors The picker colors. See [KPickerDefaults.colors].
 */
@Composable
public expect fun KPicker(
    items: List<String>,
    onItemPicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    colors: KPickerColors = KPickerDefaults.colors()
)
