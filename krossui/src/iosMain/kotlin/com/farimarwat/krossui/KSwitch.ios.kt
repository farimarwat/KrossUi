package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import com.farimarwat.krossui.utils.NativeView
import com.farimarwat.krossui.utils.toUiColorOrNull
import kotlinx.cinterop.ObjCAction
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIControlEventValueChanged
import platform.UIKit.UISwitch
import platform.darwin.NSObject

@Composable
public actual fun KSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    contentDescription: String?,
    colors: KSwitchColors
) {
    val currentOnCheckedChange by rememberUpdatedState(onCheckedChange)
    // UIControl holds targets weakly; remember keeps it alive
    val target = remember {
        object : NSObject() {
            @ObjCAction
            fun switchChanged(sender: UISwitch) {
                currentOnCheckedChange(sender.on)
            }
        }
    }

    // Natural size comes from UISwitch itself (it differs between iOS versions, e.g. wider on iOS 26)
    NativeView(
        factory = {
            UISwitch().apply {
                addTarget(target, NSSelectorFromString("switchChanged:"), UIControlEventValueChanged)
            }
        },
        update = { switch ->
            if (switch.on != checked) switch.setOn(checked, animated = true)
            switch.enabled = enabled
            switch.onTintColor = colors.trackColorChecked.toUiColorOrNull()
            // tintColor cannot be reset to null from Kotlin; set it only when a color is given
            colors.trackColorUnchecked.toUiColorOrNull()?.let { switch.tintColor = it }
            switch.thumbTintColor = colors.thumbColor.toUiColorOrNull()
        },
        modifier = modifier,
        accessibilityLabel = contentDescription
    )
}
