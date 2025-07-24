// commonMain/src/commonMain/kotlin/com/farimarwat/krossui/KOutLinedButton.kt

package com.farimarwat.krossui.components.KButton

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp

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
