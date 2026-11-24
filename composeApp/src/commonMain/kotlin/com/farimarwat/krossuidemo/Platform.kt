package com.farimarwat.krossuidemo

import androidx.compose.runtime.Composable

/**
 * Forces light/dark for the whole app (`null` = follow the system).
 * iOS sets the window appearance so native UIKit controls follow; Android relies on MaterialTheme.
 */
expect fun applyAppearanceOverride(dark: Boolean?)

/** Handles the system back gesture/button while [enabled] (Android); no-op on iOS. */
@Composable
expect fun PlatformBackHandler(enabled: Boolean, onBack: () -> Unit)
