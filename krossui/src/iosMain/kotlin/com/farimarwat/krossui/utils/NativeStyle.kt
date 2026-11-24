package com.farimarwat.krossui.utils

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.viewinterop.UIKitInteropInteractionMode
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import platform.Foundation.NSBundle
import platform.Foundation.NSNumber
import platform.UIKit.NSFontAttributeName
import platform.UIKit.UIButton
import platform.UIKit.UIButtonConfiguration
import platform.UIKit.UIButtonConfigurationCornerStyleFixed
import platform.UIKit.UIColor
import platform.UIKit.UIControlStateNormal
import platform.UIKit.UIDevice
import platform.UIKit.UIFont
import platform.UIKit.UIImage

/**
 * Interop properties used by every KrossUI UIKit view.
 *
 * `placedAsOverlay` puts the native view above the Compose canvas, so its background is
 * transparent and iOS 26 Liquid Glass can show the Compose content behind it.
 * Trade-off: Compose content cannot be drawn on top of these native views.
 *
 * The view is placed above the Compose canvas, touches are shared cooperatively with scroll containers,
 * and native accessibility is on so VoiceOver reads the UIKit control.
 */
@OptIn(ExperimentalComposeUiApi::class)
internal val NativeInteropProperties = UIKitInteropProperties(
    // Cooperative: touches that start a scroll go to the enclosing Compose scroll container
    interactionMode = UIKitInteropInteractionMode.Cooperative(),
    // VoiceOver reads the native control (its label, value and traits)
    isNativeAccessibilityEnabled = true,
    placedAsOverlay = true
)

/** True when the running iOS version is at least [major].[minor]. */
internal fun isIosAtLeast(major: Int, minor: Int = 0): Boolean {
    val parts = UIDevice.currentDevice.systemVersion.split(".")
    val currentMajor = parts.getOrNull(0)?.toIntOrNull() ?: 0
    val currentMinor = parts.getOrNull(1)?.toIntOrNull() ?: 0
    return currentMajor > major || (currentMajor == major && currentMinor >= minor)
}

/**
 * True when the app shows the iOS 26 Liquid Glass design: running on iOS 26+ and the app has not
 * opted out with the `UIDesignRequiresCompatibility` Info.plist key.
 */
internal val isLiquidGlassEnabled: Boolean by lazy {
    val optedOut = NSBundle.mainBundle.objectForInfoDictionaryKey("UIDesignRequiresCompatibility")
        .let { it == true || (it as? NSNumber)?.boolValue == true }
    isIosAtLeast(26) && !optedOut
}

/** The two native button roles KrossUI maps to. */
internal enum class ButtonKind {
    /** Primary action: prominent glass (iOS 26+), filled (iOS 15–25). */
    Filled,

    /** Secondary action: regular glass (iOS 26+), bordered (iOS 15–25). */
    Outlined
}

/**
 * Styles this button the native way for the running iOS version.
 * Every unspecified value ([Color.Unspecified], [Dp.Unspecified], [TextUnit.Unspecified]) keeps the system default.
 * Callers pass the colors for the current enabled/disabled state.
 */
internal fun UIButton.applyNativeStyle(
    kind: ButtonKind,
    title: String?,
    image: UIImage?,
    fontSize: TextUnit,
    containerColor: Color,
    contentColor: Color,
    cornerRadius: Dp = Dp.Unspecified,
    borderWidth: Dp = Dp.Unspecified,
    borderColor: Color = Color.Unspecified
) {
    if (!isIosAtLeast(15)) {
        applyLegacyStyle(
            kind,
            title,
            image,
            fontSize,
            containerColor,
            contentColor,
            cornerRadius,
            borderWidth,
            borderColor
        )
        return
    }

    val glass = isLiquidGlassEnabled
    val config = when {
        glass && kind == ButtonKind.Filled -> UIButtonConfiguration.prominentGlassButtonConfiguration()
        glass -> UIButtonConfiguration.glassButtonConfiguration()
        kind == ButtonKind.Filled -> UIButtonConfiguration.filledButtonConfiguration()
        else -> UIButtonConfiguration.borderedButtonConfiguration()
    }
    config.title = title
    config.image = image
    config.imagePadding = 6.0
    config.baseForegroundColor = contentColor.toUiColorOrNull()

    if (glass || kind == ButtonKind.Filled) {
        // The tint colors the glass / the filled background; null = system tint
        // tintColor cannot be reset to null from Kotlin; set it only when a color is given
        containerColor.toUiColorOrNull()?.let { tintColor = it }
    } else {
        containerColor.toUiColorOrNull()?.let { config.baseBackgroundColor = it }
    }

    if (fontSize != TextUnit.Unspecified) {
        val font = UIFont.systemFontOfSize(fontSize.value.toDouble())
        config.titleTextAttributesTransformer = { incoming ->
            val attributes = HashMap<Any?, Any?>()
            incoming?.let { attributes.putAll(it) }
            attributes[NSFontAttributeName] = font
            attributes
        }
    }

    // Glass shapes are system-defined; custom shape and border apply before iOS 26
    if (!glass) {
        if (cornerRadius.isSpecified) {
            config.cornerStyle = UIButtonConfigurationCornerStyleFixed
            config.background.cornerRadius = cornerRadius.value.toDouble()
        }
        if (kind == ButtonKind.Outlined && (borderWidth.isSpecified || borderColor != Color.Unspecified)) {
            config.background.strokeWidth = if (borderWidth.isSpecified) borderWidth.value.toDouble() else 1.0
            config.background.strokeColor = borderColor.toUiColorOrNull() ?: tintColor
        }
    }

    configuration = config
}

/** iOS 14: classic UIButton with a background color, since button configurations need iOS 15. */
private fun UIButton.applyLegacyStyle(
    kind: ButtonKind,
    title: String?,
    image: UIImage?,
    fontSize: TextUnit,
    containerColor: Color,
    contentColor: Color,
    cornerRadius: Dp,
    borderWidth: Dp,
    borderColor: Color
) {
    val filled = kind == ButtonKind.Filled
    setTitle(title, forState = UIControlStateNormal)
    setImage(image, forState = UIControlStateNormal)
    if (fontSize != TextUnit.Unspecified) {
        titleLabel?.font = UIFont.systemFontOfSize(fontSize.value.toDouble())
    }
    val foreground = contentColor.toUiColorOrNull() ?: if (filled) UIColor.whiteColor else tintColor
    setTitleColor(foreground, forState = UIControlStateNormal)
    imageView?.tintColor = foreground
    backgroundColor = containerColor.toUiColorOrNull() ?: if (filled) tintColor else null
    layer.cornerRadius = if (cornerRadius.isSpecified) cornerRadius.value.toDouble() else 8.0
    if (!filled) {
        layer.borderWidth = if (borderWidth.isSpecified) borderWidth.value.toDouble() else 1.0
        layer.borderColor = (borderColor.toUiColorOrNull() ?: tintColor).CGColor
    }
    clipsToBounds = true
}

/** Natural size of a styled native button. Button configurations (iOS 15+) include their own padding. */
internal fun UIButton.nativeButtonSize(): DpSize {
    val size = intrinsicDpSize()
    if (isIosAtLeast(15) || !size.width.isSpecified || !size.height.isSpecified) return size
    // Classic iOS 14 buttons have no content insets; add native-looking padding
    return DpSize(size.width + 32.dp, size.height + 16.dp)
}
