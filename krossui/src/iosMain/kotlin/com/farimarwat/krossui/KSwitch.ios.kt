package com.farimarwat.krossui

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.NativeInteropProperties
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSSelectorFromString
import platform.UIKit.*
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
public actual fun KSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    colors: KSwitchColors
) {
    // Store the callback in a remembered mutable reference
    val changeCallback = remember { mutableStateOf(onCheckedChange) }
    changeCallback.value = onCheckedChange
    val uiSwitch = remember { UISwitch().apply { on = checked } }
    // Size comes from the switch itself (it differs between iOS versions, e.g. wider on iOS 26)
    val switchSize = remember { uiSwitch.intrinsicContentSize.useContents { width to height } }

    val switchTarget = remember {
        object : NSObject() {
            @ObjCAction
            fun switchChanged() {
                changeCallback.value(uiSwitch.on)
            }
        }
    }

    UIKitView(
        properties = NativeInteropProperties,
        factory = {
            uiSwitch.addTarget(
                target = switchTarget,
                action = NSSelectorFromString("switchChanged"),
                forControlEvents = UIControlEventValueChanged
            )
            uiSwitch.accessibilityHint = switchTarget.toString()
            uiSwitch
        },
        update = { view ->
            view.apply {
                // Update switch state
                on = checked
                this.enabled = enabled
                onTintColor = colors.trackColorChecked.toUiColor()
                tintColor = colors.trackColorUnchecked.toUiColor()
                thumbTintColor = colors.thumbColor.toUiColor()

                // Alpha for disabled state
                alpha = if (enabled) 1.0 else 0.5
            }
        },
        modifier = modifier
            .defaultMinSize(minWidth = switchSize.first.dp, minHeight = switchSize.second.dp)
    )
}
