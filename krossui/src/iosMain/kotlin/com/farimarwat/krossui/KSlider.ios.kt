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
import platform.UIKit.UIControlEventTouchCancel
import platform.UIKit.UIControlEventTouchUpInside
import platform.UIKit.UIControlEventTouchUpOutside
import platform.UIKit.UIControlEventValueChanged
import platform.UIKit.UISlider
import platform.darwin.NSObject

@Composable
public actual fun KSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    valueRange: ClosedFloatingPointRange<Float>,
    steps: Int,
    onValueChangeFinished: (() -> Unit)?,
    colors: KSliderColors
) {
    val currentOnValueChange by rememberUpdatedState(onValueChange)
    val currentOnValueChangeFinished by rememberUpdatedState(onValueChangeFinished)
    val currentRange by rememberUpdatedState(valueRange)
    val currentSteps by rememberUpdatedState(steps)
    // UIControl holds targets weakly; remember keeps it alive
    val target = remember {
        object : NSObject() {
            @ObjCAction
            fun valueChanged(sender: UISlider) {
                // UISlider is continuous; snap to steps like Material's Slider
                val snapped = snapToSteps(sender.value, currentRange, currentSteps)
                if (snapped != sender.value) sender.value = snapped
                currentOnValueChange(snapped)
            }

            @ObjCAction
            fun touchEnded() {
                currentOnValueChangeFinished?.invoke()
            }
        }
    }

    // UISlider has no natural width, so it fills the available width (like Material's Slider)
    NativeView(
        factory = {
            UISlider().apply {
                addTarget(target, NSSelectorFromString("valueChanged:"), UIControlEventValueChanged)
                addTarget(
                    target,
                    NSSelectorFromString("touchEnded"),
                    UIControlEventTouchUpInside or UIControlEventTouchUpOutside or UIControlEventTouchCancel
                )
            }
        },
        update = { slider ->
            slider.minimumValue = valueRange.start
            slider.maximumValue = valueRange.endInclusive
            if (slider.value != value) slider.value = value
            slider.enabled = enabled
            slider.minimumTrackTintColor = colors.activeTrackColor.toUiColorOrNull()
            slider.maximumTrackTintColor = colors.inactiveTrackColor.toUiColorOrNull()
            slider.thumbTintColor = colors.thumbColor.toUiColorOrNull()
        },
        modifier = modifier,
        minSize = DpSize(MinTouchTarget, MinTouchTarget),
        preferredSize = { DpSize(Dp.Unspecified, it.intrinsicDpSize().height) }
    )
}
