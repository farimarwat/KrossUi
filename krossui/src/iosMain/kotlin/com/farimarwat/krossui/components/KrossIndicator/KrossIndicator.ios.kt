package com.farimarwat.krossui.components.KrossIndicator

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGAffineTransformMakeScale
import platform.CoreGraphics.CGRectMake
import platform.UIKit.UIActivityIndicatorView
import platform.UIKit.UIActivityIndicatorViewStyleLarge
import platform.UIKit.UIColor
import platform.UIKit.UIView

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun KProgressIndicator(
    size: Double,
    color: Color
) {
    val indicator = remember { UIActivityIndicatorView() }

    LaunchedEffect(size) {
        indicator.startAnimating()
        indicator.color = color.toUiColor()

        // Calculate scale factor based on desired size
        // UIActivityIndicatorView default size is about 20-22 points
        val defaultSize = 22.0
        val scaleFactor = size / defaultSize

        indicator.transform = CGAffineTransformMakeScale(scaleFactor, scaleFactor)
    }

    UIKitView(
        factory = { indicator },
        modifier = Modifier.size(size.dp)
    )
}
