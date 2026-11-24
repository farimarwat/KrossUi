package com.farimarwat.krossui.components.KOutLinedButton

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.GlassStyle
import com.farimarwat.krossui.utils.NativeInteropProperties
import com.farimarwat.krossui.utils.applyGlassConfiguration
import com.farimarwat.krossui.utils.isLiquidGlassEnabled
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.useContents
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIButton
import platform.UIKit.UIButtonTypeSystem
import platform.UIKit.UIControlEventTouchUpInside
import platform.UIKit.UIControlStateNormal
import platform.UIKit.UIFont
import platform.darwin.NSObject
import kotlin.math.max

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
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
    clickCallback.value = onClick
    val target = remember {
        object : NSObject() {
            @ObjCAction
            fun buttonTapped() {
                clickCallback.value()
            }
        }
    }

    // Measure a button styled exactly like the real one
    val minSize = remember(text, fontSize) {
        UIButton.buttonWithType(UIButtonTypeSystem).apply {
            style(text, colors, fontSize, cornerRadius, borderWidth, isEnabled = true)
        }.intrinsicContentSize.useContents {
            if (isLiquidGlassEnabled) {
                // Glass configuration already includes its own content insets
                max(width, 44.0) to max(height, 44.0)
            } else {
                max(width + 32, 44.0) to max(height + 16, 44.0)
            }
        }
    }

    val button = remember {
        UIButton.buttonWithType(UIButtonTypeSystem).apply {
            addTarget(
                target = target,
                action = NSSelectorFromString("buttonTapped"),
                forControlEvents = UIControlEventTouchUpInside
            )
        }
    }

    UIKitView(
        factory = { button },
        update = { view ->
            view.style(text, colors, fontSize, cornerRadius, borderWidth, isEnabled)
            view.enabled = isEnabled
        },
        modifier = modifier
            .defaultMinSize(minWidth = minSize.first.dp, minHeight = minSize.second.dp),
        properties = NativeInteropProperties
    )
}

/** iOS 26+: regular Liquid Glass (secondary action). Earlier iOS: bordered button. */
@OptIn(ExperimentalForeignApi::class)
private fun UIButton.style(
    text: String,
    colors: KOutLinedButtonColors,
    fontSize: TextUnit,
    cornerRadius: Double,
    borderWidth: Double,
    isEnabled: Boolean
) {
    if (isLiquidGlassEnabled) {
        // Glass handles its own capsule shape and disabled appearance; no border in iOS 26 design
        applyGlassConfiguration(
            style = GlassStyle.Regular,
            title = text,
            image = null,
            fontSize = fontSize,
            tint = colors.borderColor,
            foreground = colors.textColor
        )
        return
    }

    setTitle(text, forState = UIControlStateNormal)
    setTitleColor(
        color = (if (isEnabled) colors.textColor else colors.disabledTextColor).toUiColor(),
        forState = UIControlStateNormal
    )
    backgroundColor = (if (isEnabled) colors.backgroundColor else colors.disabledBackgroundColor).toUiColor()
    if (fontSize != TextUnit.Unspecified) {
        titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
    }
    layer.cornerRadius = cornerRadius
    layer.borderWidth = borderWidth
    layer.borderColor = (if (isEnabled) colors.borderColor else colors.disabledBorderColor).toUiColor().CGColor
    clipsToBounds = true
}
