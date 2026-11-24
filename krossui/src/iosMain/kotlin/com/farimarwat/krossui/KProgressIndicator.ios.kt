package com.farimarwat.krossui

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.NativeInteropProperties
import com.farimarwat.krossui.utils.toUiColorOrNull
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGAffineTransformMakeScale
import platform.UIKit.UIActivityIndicatorView
import platform.UIKit.UIActivityIndicatorViewStyleMedium

@Composable
public actual fun KProgressIndicator(modifier: Modifier, size: Dp, color: Color) {
    val indicator = remember {
        UIActivityIndicatorView(activityIndicatorStyle = UIActivityIndicatorViewStyleMedium).apply {
            startAnimating()
        }
    }
    // The system default color, restored when no color is given
    val defaultColor = remember { indicator.color }
    // Native size of the system spinner (≈20pt); used when no size is given
    val nativeSize = remember { indicator.intrinsicContentSize.useContents { width } }

    UIKitView(
        properties = NativeInteropProperties,
        factory = { indicator },
        update = { view ->
            view.color = color.toUiColorOrNull() ?: defaultColor
            // The spinner has a fixed native size, so a custom size is reached by scaling
            view.transform = if (size.isSpecified) {
                val scale = size.value / nativeSize
                CGAffineTransformMakeScale(scale, scale)
            } else {
                CGAffineTransformMakeScale(1.0, 1.0)
            }
        },
        modifier = modifier.size(if (size.isSpecified) size else nativeSize.dp)
    )
}
