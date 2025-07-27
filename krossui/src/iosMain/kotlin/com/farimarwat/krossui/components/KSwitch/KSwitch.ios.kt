package com.farimarwat.krossui.components.KSwitch

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSSelectorFromString
import platform.UIKit.*
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun KSwitch(
    modifier: Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    colors: KSwitchColors,
    isEnabled: Boolean
) {
    // Store the callback in a remembered mutable reference
    val changeCallback = remember { mutableStateOf(onCheckedChange) }
    changeCallback.value = onCheckedChange
    val width = 51.0
    val height = 31.0
    // Create UISwitch
    val uiSwitch = remember {
        UISwitch().apply {
            setFrame(CGRectMake(0.0,0.0, width, height) )// Standard UISwitch size
            on = checked
        }
    }

    val switchTarget = remember{
        object : NSObject() {
            @ObjCAction
            fun switchChanged() {
                changeCallback.value(uiSwitch.on)
            }
        }
    }

    UIKitView(
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
                enabled = isEnabled
                onTintColor = colors.trackColorChecked.toUiColor()
                tintColor = colors.trackColorUnchecked.toUiColor()
                thumbTintColor = colors.thumbColor.toUiColor()

                // Alpha for disabled state
                alpha = if (isEnabled) 1.0 else 0.5
            }
        },
        modifier = modifier
            .defaultMinSize(minWidth = width.dp, minHeight = height.dp)
    )
}