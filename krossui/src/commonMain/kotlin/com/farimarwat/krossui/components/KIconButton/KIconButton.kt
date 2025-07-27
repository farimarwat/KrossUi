package com.farimarwat.krossui.components.KIconButton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import org.jetbrains.compose.resources.DrawableResource

/**
 * A cross-platform icon button component for Android and iOS.
 * Supports platform-specific icons, customizable styling, and optional title text.
 *
 * @param modifier The [Modifier] to be applied to the button.
 * @param title Optional text to display with the icon.
 * @param onClick Called when the user taps the button.
 * @param colors Colors to apply for different button states.
 * @param fontSize Font size of the optional title text.
 * @param cornerRadius The radius for rounding the button corners.
 * @param isEnabled If false, the button will appear disabled and not respond to taps.
 * @param iosIcon The name of the SF Symbol to be used on iOS.
 * @param androidIcon The drawable resource to be used on Android.
 */
@Composable
expect fun KIconButton(
    modifier: Modifier = Modifier,
    title: String? = null,
    onClick: () -> Unit,
    colors: KIconButtonColors = KIconButtonDefaults.colors(),
    fontSize: TextUnit = KIconButtonDefaults.FontSize,
    cornerRadius: Double = KIconButtonDefaults.CornerRadius,
    isEnabled: Boolean = true,
    iosIcon: String? = null,
    androidIcon: DrawableResource? = null,
)
