package com.farimarwat.krossui.components.KButton

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.toUiColor
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
    colors: KButtonColors,
    fontSize: TextUnit,
    cornerRadius: Double,
    isEnabled: Boolean
) {
    BoxWithConstraints (
        modifier = modifier
    ){
        val width = maxWidth.value
        val height = maxHeight.value
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
        val button = remember {
             UIButton.buttonWithType(UIButtonTypeSystem).apply {
                 setTitle(text, forState = UIControlStateNormal)

                 // Title color
                 setTitleColor(
                     color = if(isEnabled) colors.contentColor.toUiColor() else colors.disabledContentColor.toUiColor(),
                     forState = UIControlStateNormal
                 )

                 // Background color
                 backgroundColor = if(isEnabled) colors.containerColor.toUiColor() else colors.disabledContainerColor.toUiColor()

                 // Font
                 titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
                 //Corner Radius
                 layer.cornerRadius = cornerRadius
                 clipsToBounds = true

                 // Add target-action
                 addTarget(
                     target = target,
                     action = NSSelectorFromString("buttonTapped"),
                     forControlEvents = UIControlEventTouchUpInside
                 )
                 setFrame(CGRectMake(0.0, 0.0, width.toDouble(),height.toDouble()))
             }
        }
        UIKitView(
            factory = {
                button.enabled = isEnabled

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
                        color = if(isEnabled) colors.contentColor.toUiColor() else colors.disabledContentColor.toUiColor(),
                        forState = UIControlStateNormal
                    )

                    // Update background color
                    this.backgroundColor = if(isEnabled) colors.containerColor.toUiColor() else colors.disabledContainerColor.toUiColor()
                    // Update font
                    if (fontSize != TextUnit.Unspecified) {
                        titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
                    }
                }
            },
            modifier = Modifier
                .size(width = width.dp, height=height.dp)
        )
    }
}