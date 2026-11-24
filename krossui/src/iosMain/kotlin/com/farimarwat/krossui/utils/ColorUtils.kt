package com.farimarwat.krossui.utils

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.colorspace.ColorSpaces
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.CoreGraphics.CGFloatVar
import platform.UIKit.UIColor
import platform.UIKit.UITraitCollection
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.resolvedColorWithTraitCollection

/** Converts to a UIKit color (sRGB). */
internal fun Color.toUiColor(): UIColor {
    val srgb = convert(ColorSpaces.Srgb)
    return UIColor(
        red = srgb.red.toDouble(),
        green = srgb.green.toDouble(),
        blue = srgb.blue.toDouble(),
        alpha = srgb.alpha.toDouble()
    )
}

/**
 * Converts to a UIKit color, or `null` for [Color.Unspecified].
 * Assigning `null` to a UIKit color property restores the system default (dark mode aware).
 */
internal fun Color.toUiColorOrNull(): UIColor? = if (this == Color.Unspecified) null else toUiColor()

/** Converts a UIKit color (already resolved for the current appearance) to a Compose color. */
internal fun UIColor.toComposeColor(): Color = memScoped {
    val r = alloc<CGFloatVar>()
    val g = alloc<CGFloatVar>()
    val b = alloc<CGFloatVar>()
    val a = alloc<CGFloatVar>()
    getRed(r.ptr, green = g.ptr, blue = b.ptr, alpha = a.ptr)
    Color(r.value.toFloat(), g.value.toFloat(), b.value.toFloat(), a.value.toFloat())
}

/** Uses [disabled] when the control is disabled and a disabled color was given; otherwise the system dims it. */
internal fun Color.orIfDisabled(enabled: Boolean, disabled: Color): Color =
    if (!enabled && disabled != Color.Unspecified) disabled else this

/** Resolves a dynamic UIKit system color for the current light/dark appearance, for use in Compose. */
@Composable
internal fun UIColor.resolvedForCompose(): Color {
    val dark = isSystemInDarkTheme()
    return remember(this, dark) {
        val style = if (dark) {
            UIUserInterfaceStyle.UIUserInterfaceStyleDark
        } else {
            UIUserInterfaceStyle.UIUserInterfaceStyleLight
        }
        resolvedColorWithTraitCollection(
            UITraitCollection.traitCollectionWithUserInterfaceStyle(style)
        ).toComposeColor()
    }
}
