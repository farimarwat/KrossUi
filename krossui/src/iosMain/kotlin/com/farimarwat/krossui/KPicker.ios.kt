package com.farimarwat.krossui

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.NativeInteropProperties
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.Foundation.*
import platform.UIKit.*
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
@Composable
public actual fun KPicker(
    items: List<String>,
    onItemPicked: (String) -> Unit,
    modifier: Modifier,
    colors: KPickerColors
) {
    BoxWithConstraints(modifier = modifier.height(150.dp)) {
        val width = maxWidth.value
        val height = maxHeight.value

        val dataSourceDelegate = remember { PickerDataSourceDelegate(items, colors, onItemPicked) }
        // Keep the delegate in sync with the latest parameters
        dataSourceDelegate.items = items
        dataSourceDelegate.colors = colors
        dataSourceDelegate.onItemPicked = onItemPicked

        val pickerView = remember {
            UIPickerView().apply {
                dataSource = dataSourceDelegate
                delegate = dataSourceDelegate
                backgroundColor = colors.containerColor.toUiColor()
            }
        }

        UIKitView(
            properties = NativeInteropProperties,
            factory = {
                pickerView
            },
            update = {
                it.backgroundColor = colors.containerColor.toUiColor()
                it.reloadAllComponents()
            },
            modifier = Modifier.size(width.dp, height.dp)
        )
    }
}
