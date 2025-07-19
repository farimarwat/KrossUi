package com.farimarwat.krossui.components.KOutLinedButton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit

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