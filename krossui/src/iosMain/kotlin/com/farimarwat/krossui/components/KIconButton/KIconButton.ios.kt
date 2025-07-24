package com.farimarwat.krossui.components.KIconButton

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
    BoxWithConstraints(modifier = modifier) {
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
                setFrame(CGRectMake(0.0, 0.0, width.toDouble(), height.toDouble()))
            }
        }

        UIKitView(
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

                // Update frame
                view.setFrame(CGRectMake(0.0, 0.0, width.toDouble(), height.toDouble()))
            },
            modifier = Modifier.size(width.dp, height.dp)
        )
    }
}