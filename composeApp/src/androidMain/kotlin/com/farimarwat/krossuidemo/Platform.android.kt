package com.farimarwat.krossuidemo

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable

actual fun applyAppearanceOverride(dark: Boolean?) {
    // KrossUI on Android reads colors from MaterialTheme, which App() switches directly
}

@Composable
actual fun PlatformBackHandler(enabled: Boolean, onBack: () -> Unit) {
    BackHandler(enabled = enabled, onBack = onBack)
}
