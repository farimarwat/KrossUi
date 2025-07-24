package com.farimarwat.krossui.components.KPicker

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun KPicker(
    modifier: Modifier = Modifier,
    items:List<String>,
    colors: KPickerColors = KPickerDefaults.colors(),
    onItemPicked:(String)-> Unit
)