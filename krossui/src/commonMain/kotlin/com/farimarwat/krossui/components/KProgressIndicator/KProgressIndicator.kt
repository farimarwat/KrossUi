package com.farimarwat.krossui.components.KProgressIndicator

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/**
 * A platform-specific circular progress indicator composable.
 *
 * Displays a spinning progress wheel to indicate loading or ongoing background tasks.
 * Automatically adapts to the platform’s native UI.
 *
 * @param size The diameter of the indicator in dp. Defaults to [KProgressIndicatorDefaults.DefaultSize].
 * @param color The color of the progress indicator. Defaults to [KProgressIndicatorDefaults.colors].
 */
@Composable
expect fun KProgressIndicator(
    size: Double = KProgressIndicatorDefaults.DefaultSize,
    color: Color = KProgressIndicatorDefaults.colors().color
)