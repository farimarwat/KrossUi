package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

/**
 * A slider that renders the native control on each platform
 * (Material 3 slider on Android, UISlider on iOS).
 *
 * @param value The current value, within [valueRange].
 * @param onValueChange Called with the new value while the user drags.
 * @param modifier The [Modifier] applied to the slider.
 * @param enabled Whether the slider can be dragged.
 * @param contentDescription Text read by TalkBack / VoiceOver; describe what the slider controls.
 * @param valueRange The range of selectable values.
 * @param steps Number of discrete values between the ends of [valueRange]; `0` = continuous.
 * @param onValueChangeFinished Called when the user releases the slider.
 * @param colors The slider colors. See [KSliderDefaults.colors].
 */
@Composable
public expect fun KSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    contentDescription: String? = null,
    valueRange: ClosedFloatingPointRange<Float> = 0f..1f,
    steps: Int = 0,
    onValueChangeFinished: (() -> Unit)? = null,
    colors: KSliderColors = KSliderDefaults.colors()
)

/** Snaps [value] to the nearest of [steps] + 2 evenly spaced values in [range] (no snapping when steps is 0). */
internal fun snapToSteps(value: Float, range: ClosedFloatingPointRange<Float>, steps: Int): Float {
    val clamped = value.coerceIn(range.start, range.endInclusive)
    if (steps <= 0 || range.endInclusive <= range.start) return clamped
    val stepSize = (range.endInclusive - range.start) / (steps + 1)
    val index = kotlin.math.round((clamped - range.start) / stepSize)
    return (range.start + index * stepSize).coerceIn(range.start, range.endInclusive)
}
