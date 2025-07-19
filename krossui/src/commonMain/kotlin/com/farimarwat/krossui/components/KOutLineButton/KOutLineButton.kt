package com.farimarwat.krossui.components.KOutLineButton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit

@Composable
expect fun KOutLineButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    colors: KOutLineButtonColors = KOutLineButtonDefaults.colors(),
    fontSize: TextUnit = KOutLineButtonDefaults.DefaultFontSize,
    cornerRadius: Double = KOutLineButtonDefaults.CornerRadius,
    borderWidth: Double = KOutLineButtonDefaults.BorderWidth,
    isEnabled: Boolean = true
)