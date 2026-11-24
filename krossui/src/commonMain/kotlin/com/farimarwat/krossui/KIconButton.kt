package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import org.jetbrains.compose.resources.DrawableResource

/**
 * A button with an icon and/or a title that renders the native control on each platform.
 *
 * iOS 26+: native prominent Liquid Glass button (capsule shape; corner radius and disabled colors are
 * handled by the system). Earlier iOS and Android: native filled button.
 *
 * @param onClick Called when the button is tapped.
 * @param modifier The [Modifier] applied to the button.
 * @param enabled Whether the button can be tapped.
 * @param title Optional text shown next to the icon.
 * @param iosIcon SF Symbol name used on iOS, e.g. `"star.fill"`.
 * @param androidIcon Drawable resource used on Android.
 * @param contentDescription Text read by TalkBack / VoiceOver; required when there is no [title].
 * @param colors The colors in enabled and disabled states. See [KIconButtonDefaults.colors].
 * @param fontSize The size of the title.
 * @param cornerRadius The corner radius (Android and iOS before 26).
 */
@Composable
public expect fun KIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    title: String? = null,
    iosIcon: String? = null,
    androidIcon: DrawableResource? = null,
    contentDescription: String? = null,
    colors: KIconButtonColors = KIconButtonDefaults.colors(),
    fontSize: TextUnit = KIconButtonDefaults.FontSize,
    cornerRadius: Dp = KIconButtonDefaults.CornerRadius
)
