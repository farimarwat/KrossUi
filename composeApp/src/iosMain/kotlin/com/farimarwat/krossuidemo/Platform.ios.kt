package com.farimarwat.krossuidemo

import androidx.compose.runtime.Composable
import platform.UIKit.UIApplication
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.UIWindow
import platform.UIKit.UIWindowScene

actual fun applyAppearanceOverride(dark: Boolean?) {
    val style = when (dark) {
        null -> UIUserInterfaceStyle.UIUserInterfaceStyleUnspecified
        true -> UIUserInterfaceStyle.UIUserInterfaceStyleDark
        false -> UIUserInterfaceStyle.UIUserInterfaceStyleLight
    }
    // Native UIKit controls follow the window's appearance, not MaterialTheme
    UIApplication.sharedApplication.connectedScenes
        .filterIsInstance<UIWindowScene>()
        .flatMap { scene -> scene.windows.filterIsInstance<UIWindow>() }
        .forEach { it.overrideUserInterfaceStyle = style }
}

@Composable
actual fun PlatformBackHandler(enabled: Boolean, onBack: () -> Unit) {
    // iOS has no system back button; pages use the top bar's back button
}
