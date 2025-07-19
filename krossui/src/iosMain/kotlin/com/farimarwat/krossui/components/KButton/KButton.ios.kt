package com.farimarwat.krossui.components.KButton

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
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
                color = if(isEnabled) colors.textColor.toUiColor() else colors.disabledTextColor.toUiColor(),
                forState = UIControlStateNormal
            )

            // Background color
            button.backgroundColor = if(isEnabled) colors.backgroundColor.toUiColor() else colors.disabledBackgroundColor.toUiColor()

            // Font
            button.titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
            //Corner Radius
            button.layer.cornerRadius = cornerRadius
            button.clipsToBounds = true

            // Add target-action
            button.addTarget(
                target = target,
                action = NSSelectorFromString("buttonTapped"),
                forControlEvents = UIControlEventTouchUpInside
            )

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
                    color = if(isEnabled) colors.textColor.toUiColor() else colors.disabledTextColor.toUiColor(),
                    forState = UIControlStateNormal
                )

                // Update background color
                this.backgroundColor = if(isEnabled) colors.backgroundColor.toUiColor() else colors.disabledBackgroundColor.toUiColor()
                // Update font
                if (fontSize != TextUnit.Unspecified) {
                    titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
                }
            }
        },
        modifier = modifier
    )
}