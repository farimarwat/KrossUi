package com.farimarwat.krossui

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.NativeInteropProperties
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGAffineTransformMakeScale
import platform.UIKit.UIActivityIndicatorView

@OptIn(ExperimentalForeignApi::class)
@Composable
public actual fun KProgressIndicator(modifier: Modifier, size: Dp, color: Color) {
    val indicator = remember { UIActivityIndicatorView() }

    LaunchedEffect(size) {
        indicator.startAnimating()

        // Calculate scale factor based on desired size
        // UIActivityIndicatorView default size is about 20-22 points
        val defaultSize = 22.0
        val scaleFactor = size.value / defaultSize

        indicator.transform = CGAffineTransformMakeScale(scaleFactor, scaleFactor)
    }

    UIKitView(
        properties = NativeInteropProperties,
        factory = { indicator },
        update = { view ->
            view.color = color.toUiColor()
        },
        modifier = modifier.size(size)
    )
}
