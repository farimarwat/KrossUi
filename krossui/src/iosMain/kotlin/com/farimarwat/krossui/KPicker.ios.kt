package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import com.farimarwat.krossui.utils.NativeView
import com.farimarwat.krossui.utils.intrinsicDpSize
import com.farimarwat.krossui.utils.toUiColorOrNull
import platform.UIKit.UIPickerView

/** Height of the standard iOS wheel picker, used if UIKit reports none. */
private val NativePickerHeight = 216.dp

@Composable
public actual fun KPicker(
    items: List<String>,
    onItemPicked: (String) -> Unit,
    modifier: Modifier,
    colors: KPickerColors
) {
    // Created once; kept in sync with the latest parameters (UIPickerView holds its delegate weakly)
    val delegate = remember { PickerDataSourceDelegate(items, colors, onItemPicked) }
    delegate.items = items
    delegate.colors = colors
    delegate.onItemPicked = onItemPicked

    // Fills the available width; height is the native wheel height
    NativeView(
        factory = {
            UIPickerView().apply {
                dataSource = delegate
                this.delegate = delegate
            }
        },
        update = { picker ->
            picker.backgroundColor = colors.containerColor.toUiColorOrNull()
            picker.reloadAllComponents()
        },
        modifier = modifier,
        preferredSize = {
            val height = it.intrinsicDpSize().height
            DpSize(Dp.Unspecified, if (height.isSpecified) height else NativePickerHeight)
        }
    )
}
