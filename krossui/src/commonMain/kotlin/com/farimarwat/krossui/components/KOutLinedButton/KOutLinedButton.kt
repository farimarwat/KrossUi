package com.farimarwat.krossui.components.KOutLinedButton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit

/**
 * A cross-platform outlined button composable that adapts to the native platform style.
 *
 * @param modifier The [Modifier] to apply to the button layout.
 * @param text The text displayed inside the button.
 * @param onClick The lambda function triggered on button click.
 * @param colors The color configuration for background, text, and border in enabled/disabled states.
 * @param fontSize The size of the text inside the button.
 * @param cornerRadius The corner radius of the button.
 * @param borderWidth The width of the button's border.
 * @param isEnabled Whether the button is enabled or disabled.
 */
@Composable
expect fun KOutLinedButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    colors: KOutLinedButtonColors = KOutLinedButtonDefaults.colors(),
    fontSize: TextUnit = KOutLinedButtonDefaults.DefaultFontSize,
    cornerRadius: Double = KOutLinedButtonDefaults.CornerRadius,
    borderWidth: Double = KOutLinedButtonDefaults.BorderWidth,
    isEnabled: Boolean = true
)
