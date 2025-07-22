package com.farimarwat.krossui.components

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
    label: String = "",
    thumbColor: Color = Color.White,
    trackColorChecked: Color = PlatformColors.systemBlue,
    trackColorUnchecked: Color = Color.Gray,
    labelColor: Color = Color.Black,
    labelSize: TextUnit = TextUnit.Unspecified,
    isEnabled: Boolean = true
)