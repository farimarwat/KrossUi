// commonMain/src/commonMain/kotlin/com/farimarwat/krossui/KButton.kt

package com.farimarwat.krossui.components.KButton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit

@Composable
expect fun KButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    colors: KButtonColors = KButtonDefaults.colors(),
    fontSize: TextUnit = KButtonDefaults.DefaultFontSize,
    cornerRadius: Double = KButtonDefaults.CornerRadius,
    isEnabled: Boolean = true
)
