package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import com.farimarwat.krossui.utils.MinTouchTarget
import com.farimarwat.krossui.utils.NativeView
import com.farimarwat.krossui.utils.intrinsicDpSize
import com.farimarwat.krossui.utils.toUiColorOrNull
import kotlinx.cinterop.ObjCAction
import platform.Foundation.NSSelectorFromString
import platform.UIKit.UIControlEventValueChanged
import platform.UIKit.UISlider
import platform.darwin.NSObject

@Composable
public actual fun KSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier,
    minValue: Float,
    maxValue: Float,
    colors: KSliderColors
) {
    val currentOnValueChange by rememberUpdatedState(onValueChange)
    // UIControl holds targets weakly; remember keeps it alive
    val target = remember {
        object : NSObject() {
            @ObjCAction
            fun sliderValueChanged(sender: UISlider) {
                currentOnValueChange(sender.value)
            }
        }
    }

    // UISlider has no natural width, so it fills the available width (like Material's Slider)
    NativeView(
        factory = {
            UISlider().apply {
                addTarget(target, NSSelectorFromString("sliderValueChanged:"), UIControlEventValueChanged)
            }
        },
        update = { slider ->
            slider.minimumValue = minValue
            slider.maximumValue = maxValue
            if (slider.value != value) slider.value = value
            slider.minimumTrackTintColor = colors.activeTrackColor.toUiColorOrNull()
            slider.maximumTrackTintColor = colors.inactiveTrackColor.toUiColorOrNull()
            slider.thumbTintColor = colors.thumbColor.toUiColorOrNull()
        },
        modifier = modifier,
        minSize = DpSize(MinTouchTarget, MinTouchTarget),
        preferredSize = { DpSize(Dp.Unspecified, it.intrinsicDpSize().height) }
    )
}
