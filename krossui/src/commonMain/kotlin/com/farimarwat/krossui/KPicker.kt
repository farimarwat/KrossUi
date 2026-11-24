package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A scrolling wheel picker for a list of strings
 * (UIPickerView on iOS, a wheel-style list on Android).
 *
 * The picker is controlled: it shows [selectedIndex] and reports the user's choice through
 * [onSelectedIndexChange]; keep the index in your state (e.g. `rememberSaveable`).
 *
 * @param items The values to choose from.
 * @param selectedIndex The index of the selected item.
 * @param onSelectedIndexChange Called with the index the user selected.
 * @param modifier The [Modifier] applied to the picker.
 * @param contentDescription Text read by TalkBack / VoiceOver; describe what is being picked.
 * @param colors The picker colors. See [KPickerDefaults.colors].
 */
@Composable
public expect fun KPicker(
    items: List<String>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    colors: KPickerColors = KPickerDefaults.colors()
)
