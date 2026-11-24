package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

/**
 * A filled button that renders the native control on each platform.
 *
 * Android: Material 3 filled button. iOS 26+: native prominent Liquid Glass button (capsule shape,
 * [cornerRadius] and disabled colors are handled by the system). Earlier iOS: native filled button.
 *
 * @param text The label shown inside the button.
 * @param onClick Called when the button is tapped.
 * @param modifier The [Modifier] applied to the button.
 * @param enabled Whether the button can be tapped.
 * @param colors The colors in enabled and disabled states. See [KButtonDefaults.colors].
 * @param fontSize The size of the label.
 * @param cornerRadius The corner radius (Android and iOS before 26).
 */
@Composable
public expect fun KButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: KButtonColors = KButtonDefaults.colors(),
    fontSize: TextUnit = KButtonDefaults.FontSize,
    cornerRadius: Dp = KButtonDefaults.CornerRadius
)
