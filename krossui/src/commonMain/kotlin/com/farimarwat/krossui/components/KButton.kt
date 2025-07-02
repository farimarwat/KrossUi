// commonMain/src/commonMain/kotlin/com/farimarwat/krossui/KButton.kt

package com.farimarwat.krossui.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit

@Composable
expect fun KButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    textColor: Color = Color.White,
    backgroundColor: Color = Color.Blue,
    fontSize: TextUnit = TextUnit.Unspecified,
    isEnabled: Boolean = true
)
