package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

/**
 * An outlined (secondary) button that renders the native control on each platform.
 *
 * Android: Material 3 outlined button. iOS 26+: native regular Liquid Glass button (capsule shape, no border;
 * [cornerRadius], [borderWidth] and disabled colors are handled by the system). Earlier iOS: native bordered button.
 *
 * @param text The label shown inside the button.
 * @param onClick Called when the button is tapped.
 * @param modifier The [Modifier] applied to the button.
 * @param enabled Whether the button can be tapped.
 * @param colors The colors in enabled and disabled states. See [KOutlinedButtonDefaults.colors].
 * @param fontSize The size of the label.
 * @param cornerRadius The corner radius (Android and iOS before 26).
 * @param borderWidth The border width (Android and iOS before 26).
 */
@Composable
public expect fun KOutlinedButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: KOutlinedButtonColors = KOutlinedButtonDefaults.colors(),
    fontSize: TextUnit = KOutlinedButtonDefaults.FontSize,
    cornerRadius: Dp = KOutlinedButtonDefaults.CornerRadius,
    borderWidth: Dp = KOutlinedButtonDefaults.BorderWidth
)
