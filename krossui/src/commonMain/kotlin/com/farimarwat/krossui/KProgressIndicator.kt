package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp

/**
 * An indeterminate (spinning) progress indicator that renders the native control on each platform
 * (Material 3 circular indicator on Android, UIActivityIndicatorView on iOS).
 *
 * @param modifier The [Modifier] applied to the indicator.
 * @param contentDescription Text read by TalkBack / VoiceOver; e.g. "Loading".
 * @param size The diameter of the indicator.
 * @param color The indicator color.
 */
@Composable
public expect fun KProgressIndicator(
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    size: Dp = KProgressIndicatorDefaults.Size,
    color: Color = KProgressIndicatorDefaults.colors().color
)
