package com.farimarwat.krossui.components.KSwitch

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
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

    UIKitView(
        factory = {
            // Create a container view
            val containerView = UIView(frame = CGRectMake(0.0, 0.0, 300.0, 44.0))

            // Create UISwitch
            val uiSwitch = UISwitch()
            uiSwitch.setFrame(CGRectMake(0.0,0.0, 51.0, 31.0) )// Standard UISwitch size
            uiSwitch.on = checked

            // Create target for switch action
            val switchTarget = object : NSObject() {
                @ObjCAction
                fun switchChanged() {
                    changeCallback.value(uiSwitch.on)
                }
            }

            // Setup switch action
            uiSwitch.addTarget(
                target = switchTarget,
                action = NSSelectorFromString("switchChanged"),
                forControlEvents = UIControlEventValueChanged
            )

            // Add switch to container
            containerView.addSubview(uiSwitch)

            // Store target reference to prevent garbage collection
            uiSwitch.accessibilityHint = switchTarget.toString()

            containerView
        },
        update = { view ->
            view.let { containerView ->
                // Update switch (find UISwitch in subviews)
                val uiSwitch = containerView.subviews.firstOrNull { it is UISwitch } as? UISwitch
                uiSwitch?.apply {
                    // Update switch state
                    on = checked
                    enabled = isEnabled
                    onTintColor = colors.trackColorChecked.toUiColor()
                    tintColor = colors.trackColorUnchecked.toUiColor()
                    thumbTintColor = colors.thumbColor.toUiColor()

                    // Alpha for disabled state
                    alpha = if (isEnabled) 1.0 else 0.5
                }
            }
        },
        modifier = modifier
    )
}