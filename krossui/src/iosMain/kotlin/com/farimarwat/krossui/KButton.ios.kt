package com.farimarwat.krossui

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.ButtonKind
import com.farimarwat.krossui.utils.NativeInteropProperties
import com.farimarwat.krossui.utils.applyNativeStyle
import com.farimarwat.krossui.utils.nativeButtonMinSize
import com.farimarwat.krossui.utils.orIfDisabled
import kotlinx.cinterop.ObjCAction
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIButton
import platform.UIKit.UIButtonTypeSystem
import platform.UIKit.UIControlEventTouchUpInside
import platform.darwin.NSObject

@Composable
public actual fun KButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    colors: KButtonColors,
    fontSize: TextUnit,
    cornerRadius: Dp
) {
    val clickCallback = remember { mutableStateOf(onClick) }
    clickCallback.value = onClick
    val target = remember {
        object : NSObject() {
            @ObjCAction
            fun buttonTapped() {
                clickCallback.value()
            }
        }
    }

    // Filled (iOS 15–25) or prominent glass (iOS 26+); unspecified values keep the system look
    val style: UIButton.() -> Unit = {
        applyNativeStyle(
            kind = ButtonKind.Filled,
            title = text,
            image = null,
            fontSize = fontSize,
            containerColor = colors.containerColor.orIfDisabled(enabled, colors.disabledContainerColor),
            contentColor = colors.contentColor.orIfDisabled(enabled, colors.disabledContentColor),
            cornerRadius = cornerRadius
        )
    }
    val minSize = remember(text, fontSize) { nativeButtonMinSize(style) }

    val button = remember {
        UIButton.buttonWithType(UIButtonTypeSystem).apply {
            addTarget(target, NSSelectorFromString("buttonTapped"), UIControlEventTouchUpInside)
        }
    }

    UIKitView(
        factory = { button },
        update = { view ->
            view.style()
            view.enabled = enabled
        },
        modifier = modifier.defaultMinSize(minWidth = minSize.first.dp, minHeight = minSize.second.dp),
        properties = NativeInteropProperties
    )
}
