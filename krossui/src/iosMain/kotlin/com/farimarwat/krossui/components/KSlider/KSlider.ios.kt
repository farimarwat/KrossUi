package com.farimarwat.krossui.components.KSlider

import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import com.farimarwat.krossui.utils.toUiColor
import platform.UIKit.UISlider

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIControlEventValueChanged
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun KSlider(
    modifier: Modifier,
    value: Float,
    minValue: Float,
    maxValue: Float,
    colors: KSliderColors,
    onValueChanged: (Float) -> Unit
) {
    val valueChangedCallback = remember { mutableStateOf(onValueChanged) }

    val target = remember {
        object : NSObject() {
            @ObjCAction
            fun sliderValueChanged(sender: UISlider) {
                valueChangedCallback.value(sender.value)
            }
        }
    }

    // Update the callback reference when it changes
    valueChangedCallback.value = onValueChanged

    UIKitView(
        factory = {
            val slider = UISlider().apply {
                minimumValue = minValue
                maximumValue = maxValue
                this.value = value

                minimumTrackTintColor = colors.activeTrackColor.toUiColor()
                maximumTrackTintColor = colors.inactiveTrackColor.toUiColor()
                thumbTintColor = colors.thumbColor.toUiColor()

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
            slider.minimumTrackTintColor = colors.activeTrackColor.toUiColor()
            slider.maximumTrackTintColor = colors.inactiveTrackColor.toUiColor()
            slider.thumbTintColor = colors.thumbColor.toUiColor()

            // Update range if it changes
            slider.minimumValue = minValue
            slider.maximumValue = maxValue
        }
    )
}