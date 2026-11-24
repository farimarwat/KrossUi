package com.farimarwat.krossui.utils

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import platform.Foundation.NSBundle
import platform.Foundation.NSNumber
import platform.UIKit.NSFontAttributeName
import platform.UIKit.UIButton
import platform.UIKit.UIButtonConfiguration
import platform.UIKit.UIDevice
import platform.UIKit.UIFont
import platform.UIKit.UIImage

/**
 * Interop properties used by every KrossUI UIKit view.
 *
 * `placedAsOverlay` puts the native view above the Compose canvas, so its background is
 * transparent and iOS 26 Liquid Glass can show the Compose content behind it.
 * Trade-off: Compose content cannot be drawn on top of these native views.
 */
@OptIn(ExperimentalComposeUiApi::class)
internal val NativeInteropProperties = UIKitInteropProperties(placedAsOverlay = true)

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

internal enum class GlassStyle { Prominent, Regular }

/**
 * Styles this button with the native iOS 26 glass configuration.
 * Only call when [isLiquidGlassEnabled] is true.
 */
internal fun UIButton.applyGlassConfiguration(
    style: GlassStyle,
    title: String?,
    image: UIImage?,
    fontSize: TextUnit,
    tint: Color,
    foreground: Color
) {
    val config = when (style) {
        GlassStyle.Prominent -> UIButtonConfiguration.prominentGlassButtonConfiguration()
        GlassStyle.Regular -> UIButtonConfiguration.glassButtonConfiguration()
    }
    config.title = title
    config.image = image
    config.imagePadding = 6.0
    config.baseForegroundColor = foreground.toUiColor()

    if (fontSize != TextUnit.Unspecified) {
        val font = UIFont.systemFontOfSize(fontSize.value.toDouble())
        config.titleTextAttributesTransformer = { incoming ->
            val attributes = HashMap<Any?, Any?>()
            incoming?.let { attributes.putAll(it) }
            attributes[NSFontAttributeName] = font
            attributes
        }
    }

    tintColor = tint.toUiColor()
    configuration = config
}
