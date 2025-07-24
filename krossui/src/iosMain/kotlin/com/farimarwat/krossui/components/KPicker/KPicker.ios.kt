package com.farimarwat.krossui.components.KPicker

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.*
import platform.CoreGraphics.CGRectMake
import platform.Foundation.*
import platform.UIKit.*
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
@Composable
actual fun KPicker(
    modifier: Modifier,
    items: List<String>,
    colors: KPickerColors,
    onItemPicked: (String) -> Unit
) {
    BoxWithConstraints(modifier = modifier.height(150.dp)) {
        val width = maxWidth.value
        val height = maxHeight.value

        val dataSourceDelegate = remember { PickerDataSourceDelegate(items, onItemPicked) }

        val pickerView = remember {
            UIPickerView().apply {
                dataSource = dataSourceDelegate
                delegate = dataSourceDelegate
                backgroundColor = colors.containerColor.toUiColor()
            }
        }

        UIKitView(
            factory = {
                pickerView
            },
            update = {
                it.reloadAllComponents()
            },
            modifier = Modifier.size(width.dp, height.dp)
        )
    }
}
