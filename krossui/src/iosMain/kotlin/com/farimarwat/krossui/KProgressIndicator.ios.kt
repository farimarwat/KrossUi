package com.farimarwat.krossui

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.isSpecified
import com.farimarwat.krossui.utils.NativeView
import com.farimarwat.krossui.utils.toUiColorOrNull
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGAffineTransformMakeScale
import platform.UIKit.UIActivityIndicatorView
import platform.UIKit.UIActivityIndicatorViewStyleMedium

@Composable
public actual fun KProgressIndicator(modifier: Modifier, size: Dp, color: Color) {
    val indicator = remember {
        UIActivityIndicatorView(activityIndicatorStyle = UIActivityIndicatorViewStyleMedium).apply { startAnimating() }
    }
    // System defaults, restored when no color / size is given
    val defaultColor = remember { indicator.color }
    val nativeSize = remember { indicator.intrinsicContentSize.useContents { width } }

    NativeView(
        factory = { indicator },
        update = { view ->
            view.color = color.toUiColorOrNull() ?: defaultColor
            // The spinner has a fixed native size, so a custom size is reached by scaling
            val scale = if (size.isSpecified) size.value / nativeSize else 1.0
            view.transform = CGAffineTransformMakeScale(scale, scale)
        },
        // Natural size is the native spinner (≈20pt); a given size overrides it
        modifier = if (size.isSpecified) modifier.size(size) else modifier
    )
}
