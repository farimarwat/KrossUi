package com.farimarwat.krossui

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.GlassStyle
import com.farimarwat.krossui.utils.NativeInteropProperties
import com.farimarwat.krossui.utils.applyGlassConfiguration
import com.farimarwat.krossui.utils.isLiquidGlassEnabled
import com.farimarwat.krossui.utils.toUiColor
import kotlin.math.max
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import kotlinx.cinterop.useContents
import org.jetbrains.compose.resources.DrawableResource
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIButton
import platform.UIKit.UIButtonTypeSystem
import platform.UIKit.UIControlEventTouchUpInside
import platform.UIKit.UIControlStateNormal
import platform.UIKit.UIFont
import platform.UIKit.UIImage
import platform.UIKit.UIViewContentMode
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
@Composable
public actual fun KIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    title: String?,
    iosIcon: String?,
    androidIcon: DrawableResource?,
    colors: KIconButtonColors,
    fontSize: TextUnit,
    cornerRadius: Dp
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
    val minSize = remember(title, iosIcon, fontSize) {
        UIButton.buttonWithType(UIButtonTypeSystem).apply {
            style(title, iosIcon, colors, fontSize, cornerRadius.value.toDouble(), isEnabled = true)
        }.intrinsicContentSize.useContents {
            if (isLiquidGlassEnabled || title.isNullOrBlank()) {
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
            view.style(title, iosIcon, colors, fontSize, cornerRadius.value.toDouble(), enabled)
            view.enabled = enabled
        },
        modifier = modifier
            .defaultMinSize(minWidth = minSize.first.dp, minHeight = minSize.second.dp),
        properties = NativeInteropProperties
    )
}

/** iOS 26+: prominent Liquid Glass with SF Symbol and/or title. Earlier iOS: filled button. */
@OptIn(ExperimentalForeignApi::class)
private fun UIButton.style(
    title: String?,
    iosIcon: String?,
    colors: KIconButtonColors,
    fontSize: TextUnit,
    cornerRadius: Double,
    isEnabled: Boolean
) {
    val image = iosIcon?.takeIf { it.isNotBlank() }?.let { UIImage.systemImageNamed(it) }
    val text = title?.takeIf { it.isNotBlank() }

    if (isLiquidGlassEnabled) {
        applyGlassConfiguration(
            style = GlassStyle.Prominent,
            title = text,
            image = image,
            fontSize = fontSize,
            tint = colors.containerColor,
            foreground = colors.contentColor
        )
        return
    }

    val content = (if (isEnabled) colors.contentColor else colors.disabledContentColor).toUiColor()
    backgroundColor = (if (isEnabled) colors.containerColor else colors.disabledContainerColor).toUiColor()
    layer.cornerRadius = cornerRadius
    clipsToBounds = true

    setTitle(text, forState = UIControlStateNormal)
    setTitleColor(content, forState = UIControlStateNormal)
    if (fontSize != TextUnit.Unspecified) {
        titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
    }

    setImage(image, forState = UIControlStateNormal)
    imageView?.contentMode = UIViewContentMode.UIViewContentModeScaleAspectFit
    tintColor = content
}
