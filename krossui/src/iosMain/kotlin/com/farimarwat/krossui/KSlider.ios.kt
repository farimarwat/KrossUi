package com.farimarwat.krossui

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.NativeInteropProperties
import com.farimarwat.krossui.utils.toUiColorOrNull
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIControlEventValueChanged
import platform.UIKit.UISlider
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
public actual fun KSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier,
    minValue: Float,
    maxValue: Float,
    colors: KSliderColors
) {
    val valueChangedCallback = remember { mutableStateOf(onValueChange) }

    val target = remember {
        object : NSObject() {
            @ObjCAction
            fun sliderValueChanged(sender: UISlider) {
                valueChangedCallback.value(sender.value)
            }
        }
    }

    // Update the callback reference when it changes
    valueChangedCallback.value = onValueChange

    UIKitView(
        properties = NativeInteropProperties,
        factory = {
            val slider = UISlider().apply {
                minimumValue = minValue
                maximumValue = maxValue
                this.value = value

                minimumTrackTintColor = colors.activeTrackColor.toUiColorOrNull()
                maximumTrackTintColor = colors.inactiveTrackColor.toUiColorOrNull()
                thumbTintColor = colors.thumbColor.toUiColorOrNull()

                // Add target for value changed events
                addTarget(
                    target = target,
                    action = NSSelectorFromString("sliderValueChanged:"),
                    forControlEvents = UIControlEventValueChanged
                )
            }

            // Return the UISlider instance
            slider
        },
        modifier = modifier
            .defaultMinSize(minWidth = 150.dp, minHeight = 44.dp), // Ensure minimum usable size
        update = { slider ->
            if (slider.value != value) {
                slider.value = value
            }

            // Update colors if they change
            slider.minimumTrackTintColor = colors.activeTrackColor.toUiColorOrNull()
            slider.maximumTrackTintColor = colors.inactiveTrackColor.toUiColorOrNull()
            slider.thumbTintColor = colors.thumbColor.toUiColorOrNull()

            // Update range if it changes
            slider.minimumValue = minValue
            slider.maximumValue = maxValue
        }
    )
}
