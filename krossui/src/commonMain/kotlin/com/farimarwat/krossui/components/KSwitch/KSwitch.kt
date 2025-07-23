package com.farimarwat.krossui.components.KSwitch

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

@Composable
expect fun KSwitch(
    modifier: Modifier = Modifier,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    thumbColor: Color = Color.White,
    trackColorChecked: Color = PlatformColors.systemBlue,
    trackColorUnchecked: Color = Color.Gray,
    isEnabled: Boolean = true
)