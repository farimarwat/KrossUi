package com.farimarwat.krossui.components.KIconButton

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.*
import org.jetbrains.compose.resources.DrawableResource
import platform.CoreGraphics.*
import platform.Foundation.*
import platform.UIKit.*
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)
@Composable
actual fun KIconButton(
    modifier: Modifier,
    title: String?,
    onClick: () -> Unit,
    colors: KIconButtonColors,
    fontSize: TextUnit,
    cornerRadius: Double,
    isEnabled: Boolean,
    iosIcon: String?,
    androidIcon: DrawableResource?
) {
    // Calculate minimum dimensions based on content
    val minSize = remember(title, iosIcon, fontSize) {
        when {
            !title.isNullOrBlank() -> {
                // For text buttons, calculate based on text size
                val tempButton = UIButton().apply {
                    setTitle(title, forState = UIControlStateNormal)
                    titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
                    sizeToFit()
                }

                tempButton.intrinsicContentSize.useContents {
                    IntSize(
                        width = kotlin.math.max(width.toInt() + 32, 44), // Add padding + minimum touch target
                        height = kotlin.math.max(height.toInt() + 16, 44) // Add padding + minimum touch target
                    )
                }
            }
            !iosIcon.isNullOrBlank() -> {
                // For icon buttons, use standard icon button size
                IntSize(
                    width = 44, // Standard iOS touch target
                    height = 44
                )
            }
            else -> {
                // Fallback minimum size
                IntSize(width = 44, height = 44)
            }
        }
    }

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
        UIButton().apply {
            backgroundColor = if (isEnabled) colors.containerColor.toUiColor() else colors.disabledContainerColor.toUiColor()
            layer.cornerRadius = cornerRadius
            clipsToBounds = true

            // Configure for title or icon
            if (!title.isNullOrBlank()) {
                setTitle(title, forState = UIControlStateNormal)
                setTitleColor(
                    color = if (isEnabled) colors.contentColor.toUiColor() else colors.disabledContentColor.toUiColor(),
                    forState = UIControlStateNormal
                )
                titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
            }
            if (!iosIcon.isNullOrBlank()) {
                // Use systemImageNamed for SF Symbols
                val image = UIImage.systemImageNamed(iosIcon)
                setImage(image, forState = UIControlStateNormal)
                // Set tint color for the icon
                tintColor = if (isEnabled) colors.contentColor.toUiColor() else colors.disabledContentColor.toUiColor()

                // Configure image rendering
                imageView?.contentMode = UIViewContentMode.UIViewContentModeScaleAspectFit
                imageView?.tintColor = tintColor
            }

            addTarget(
                target = target,
                action = NSSelectorFromString("buttonTapped"),
                forControlEvents = UIControlEventTouchUpInside
            )
        }
    }

    UIKitView(
        modifier = modifier
            .defaultMinSize(minWidth = minSize.width.dp, minHeight = minSize.height.dp),
        factory = {
            button.enabled = isEnabled
            button.accessibilityHint = target.toString()
            button
        },
        update = { view ->
            view.enabled = isEnabled
            view.backgroundColor = if (isEnabled) colors.containerColor.toUiColor() else colors.disabledContainerColor.toUiColor()

            if (!title.isNullOrBlank()) {
                view.setTitle(title, forState = UIControlStateNormal)
                view.setTitleColor(
                    color = if (isEnabled) colors.contentColor.toUiColor() else colors.disabledContentColor.toUiColor(),
                    forState = UIControlStateNormal
                )
                view.titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
            } else if (!iosIcon.isNullOrBlank()) {
                // Consistent use of systemImageNamed
                val image = UIImage.systemImageNamed(iosIcon)
                view.setImage(image, forState = UIControlStateNormal)

                // Update tint color
                view.tintColor = if (isEnabled) colors.contentColor.toUiColor() else colors.disabledContentColor.toUiColor()
                view.imageView?.tintColor = view.tintColor
            }
        }
    )
}