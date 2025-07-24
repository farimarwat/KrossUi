package com.farimarwat.krossui.components.KIconButton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import org.jetbrains.compose.resources.DrawableResource

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