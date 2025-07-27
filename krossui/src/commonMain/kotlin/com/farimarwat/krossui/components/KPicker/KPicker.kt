package com.farimarwat.krossui.components.KPicker

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A platform-specific picker component for Kotlin Multiplatform.
 * Allows selection from a list of strings and handles the picked item via callback.
 *
 * @param modifier Modifier to be applied to the picker component.
 * @param items List of selectable items shown in the picker.
 * @param colors Color scheme for the picker in different states.
 * @param onItemPicked Callback function triggered when an item is selected.
 */
@Composable
expect fun KPicker(
    modifier: Modifier = Modifier,
    items: List<String>,
    colors: KPickerColors = KPickerDefaults.colors(),
    onItemPicked: (String) -> Unit
)