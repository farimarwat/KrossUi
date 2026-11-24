package com.farimarwat.krossui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.isSpecified

@Composable
public actual fun KProgressIndicator(modifier: Modifier, size: Dp, color: Color) {
    CircularProgressIndicator(
        modifier = if (size.isSpecified) modifier.size(size) else modifier,
        color = color.takeOrElse { ProgressIndicatorDefaults.circularColor }
    )
}
