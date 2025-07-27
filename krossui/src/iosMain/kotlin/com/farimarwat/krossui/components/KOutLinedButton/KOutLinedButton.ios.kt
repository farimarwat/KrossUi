package com.farimarwat.krossui.components.KOutLinedButton

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.useContents
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIButton
import platform.UIKit.UIButtonTypeSystem
import platform.UIKit.UIControlEventTouchUpInside
import platform.UIKit.UIControlStateNormal
import platform.UIKit.UIFont
import platform.UIKit.accessibilityHint
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun KOutLinedButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    colors: KOutLinedButtonColors,
    fontSize: TextUnit,
    cornerRadius: Double,
    borderWidth: Double,
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

    // Pre-calculate the size using a temporary button
    val buttonSize = remember(text, fontSize) {
        val tempButton = UIButton.buttonWithType(UIButtonTypeSystem).apply {
            setTitle(text, forState = UIControlStateNormal)
            titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
            sizeToFit()
        }

        val intrinsicSize = tempButton.intrinsicContentSize.useContents {
            IntSize(
                width = kotlin.math.max(width.toInt() + 32, 44), // Add padding + minimum touch target
                height = kotlin.math.max(height.toInt() + 16, 44) // Add padding + minimum touch target
            )
        }
        intrinsicSize
    }

    val button = remember {
        UIButton.buttonWithType(UIButtonTypeSystem).apply {
            setTitle(text, forState = UIControlStateNormal)

            // Title color
            setTitleColor(
                color = if(isEnabled) colors.textColor.toUiColor() else colors.disabledTextColor.toUiColor(),
                forState = UIControlStateNormal
            )

            // Background color
            backgroundColor = if(isEnabled) colors.backgroundColor.toUiColor() else colors.disabledBackgroundColor.toUiColor()

            // Font
            titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())

            //Corner Radius
            layer.cornerRadius = cornerRadius
            clipsToBounds = true

            //Border
            layer.borderColor = if(isEnabled) colors.borderColor.toUiColor().CGColor else colors.disabledBorderColor?.toUiColor()?.CGColor ?: colors.borderColor.toUiColor().CGColor
            layer.borderWidth = borderWidth

            // Add target-action
            addTarget(
                target = target,
                action = NSSelectorFromString("buttonTapped"),
                forControlEvents = UIControlEventTouchUpInside
            )
        }
    }

    UIKitView(
        factory = {
            button.enabled = isEnabled
            // Store target to prevent garbage collection
            button.accessibilityHint = target.toString()
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

                // Update border color for enabled/disabled state
                layer.borderColor = if(isEnabled) colors.borderColor.toUiColor().CGColor else colors.disabledBorderColor?.toUiColor()?.CGColor ?: colors.borderColor.toUiColor().CGColor

                // Update font
                if (fontSize != TextUnit.Unspecified) {
                    titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
                }
            }
        },
        modifier = Modifier
            .size(width = buttonSize.width.dp, height = buttonSize.height.dp)
            .then(modifier)
    )
}