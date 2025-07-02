package com.farimarwat.krossui.components

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.UIKitView
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
    label: String,
    thumbColor: Color,
    trackColorChecked: Color,
    trackColorUnchecked: Color,
    labelColor: Color,
    labelSize: TextUnit,
    isEnabled: Boolean
) {
    // Store the callback in a remembered mutable reference
    val changeCallback = remember { mutableStateOf(onCheckedChange) }
    changeCallback.value = onCheckedChange

    UIKitView(
        factory = {
            // Create a container view
            val containerView = UIView(frame = CGRectMake(0.0, 0.0, 300.0, 44.0))

            // Create label if provided
            var labelView: UILabel? = null
            if (label.isNotEmpty()) {
                labelView = UILabel(frame = CGRectMake(0.0, 0.0, 200.0, 44.0))
                labelView.text = label
                labelView.textAlignment = NSTextAlignmentLeft
                containerView.addSubview(labelView)
            }

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
                // Update label if it exists (first subview might be label)
                val labelView = containerView.subviews.firstOrNull { it is UILabel } as? UILabel
                labelView?.apply {
                    text = label
                    textColor = UIColor(
                        red = labelColor.red.toDouble(),
                        green = labelColor.green.toDouble(),
                        blue = labelColor.blue.toDouble(),
                        alpha = if (isEnabled) labelColor.alpha.toDouble() else labelColor.alpha.toDouble() * 0.5
                    )

                    if (labelSize != TextUnit.Unspecified) {
                        font = UIFont.systemFontOfSize(labelSize.value.toDouble())
                    }
                }

                // Update switch (find UISwitch in subviews)
                val uiSwitch = containerView.subviews.firstOrNull { it is UISwitch } as? UISwitch
                uiSwitch?.apply {
                    // Update switch state
                    on = checked
                    enabled = isEnabled

                    // Update colors
                    onTintColor = UIColor(
                        red = trackColorChecked.red.toDouble(),
                        green = trackColorChecked.green.toDouble(),
                        blue = trackColorChecked.blue.toDouble(),
                        alpha = trackColorChecked.alpha.toDouble()
                    )

                    // Note: iOS UISwitch thumb color (thumbTintColor) is available from iOS 13+
                    // and track color when off (backgroundColor) has limited customization
                    thumbTintColor = UIColor(
                        red = thumbColor.red.toDouble(),
                        green = thumbColor.green.toDouble(),
                        blue = thumbColor.blue.toDouble(),
                        alpha = thumbColor.alpha.toDouble()
                    )

                    // Alpha for disabled state
                    alpha = if (isEnabled) 1.0 else 0.5
                }
            }
        },
        modifier = modifier
    )
}