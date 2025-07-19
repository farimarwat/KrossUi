package com.farimarwat.krossui.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.UIKitView
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSSelectorFromString
import platform.UIKit.*
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
@Composable
actual fun KButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    textColor: Color,
    backgroundColor: Color,
    fontSize: TextUnit,
    isEnabled: Boolean
) {
    val clickCallback = remember { mutableStateOf(onClick) }
    val target = remember {
       object : NSObject() {
            @ObjCAction
            fun buttonTapped() {
                clickCallback.value()
            }
        }

    }
    clickCallback.value = onClick

    UIKitView(
        factory = {
            val button = UIButton.buttonWithType(UIButtonTypeSystem)


            button.setTitle(text, forState = UIControlStateNormal)

            // Title color
            button.setTitleColor(
                UIColor(
                    red = textColor.red.toDouble(),
                    green = textColor.green.toDouble(),
                    blue = textColor.blue.toDouble(),
                    alpha = textColor.alpha.toDouble()
                ),
                forState = UIControlStateNormal
            )

            // Background color
            button.backgroundColor = UIColor(
                red = backgroundColor.red.toDouble(),
                green = backgroundColor.green.toDouble(),
                blue = backgroundColor.blue.toDouble(),
                alpha = backgroundColor.alpha.toDouble()
            )

            // Font
            if (fontSize != TextUnit.Unspecified) {
                button.titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
            }

            // Add target-action
            button.addTarget(
                target = target,
                action = NSSelectorFromString("buttonTapped"),
                forControlEvents = UIControlEventTouchUpInside
            )

            button.enabled = isEnabled
            button.layer.cornerRadius = 8.0

            // Store target to prevent garbage collection
            button.accessibilityHint = target.toString() // Simple way to keep reference

            button
        },
        update = { view ->
            view.apply {
                setTitle(text, forState = UIControlStateNormal)
                enabled = isEnabled

                // Update title color
                setTitleColor(
                    UIColor(
                        red = textColor.red.toDouble(),
                        green = textColor.green.toDouble(),
                        blue = textColor.blue.toDouble(),
                        alpha = textColor.alpha.toDouble()
                    ),
                    forState = UIControlStateNormal
                )

                // Update background color
                this.backgroundColor = UIColor(
                    red = backgroundColor.red.toDouble(),
                    green = backgroundColor.green.toDouble(),
                    blue = backgroundColor.blue.toDouble(),
                    alpha = backgroundColor.alpha.toDouble()
                )

                // Update font
                if (fontSize != TextUnit.Unspecified) {
                    titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
                }
            }
        },
        modifier = modifier
    )
}