// commonMain/src/commonMain/kotlin/com/farimarwat/krossui/KOutLinedButton.kt

package com.farimarwat.krossui.components.KButton

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

/**
 * A cross-platform button composable that adapts to the native design on each platform.
 *
 * @param modifier The [Modifier] to be applied to the button.
 * @param text The text label to display inside the button.
 * @param onClick The lambda to invoke when the button is clicked.
 * @param colors The color configuration for the button in enabled and disabled states.
 * @param fontSize The size of the text label inside the button.
 * @param cornerRadius The radius of the button's corners, in dp.
 * @param isEnabled Whether the button is enabled or disabled.
 */
@Composable
expect fun KButton(
    modifier: Modifier,
    text: String,
    onClick: () -> Unit,
    colors: KButtonColors = KButtonDefaults.colors(),
    fontSize: TextUnit = KButtonDefaults.DefaultFontSize,
    cornerRadius: Double = KButtonDefaults.CornerRadius,
    isEnabled: Boolean = true
)

