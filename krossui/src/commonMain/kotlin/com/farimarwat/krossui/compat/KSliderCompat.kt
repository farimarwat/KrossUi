@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KSlider

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KSliderColors")
)
public typealias KSliderColors = com.farimarwat.krossui.KSliderColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KSliderDefaults")
)
public typealias KSliderDefaults = com.farimarwat.krossui.KSliderDefaults

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KSlider(value = value, onValueChange = onValueChanged, modifier = modifier, minValue = minValue, maxValue = maxValue, colors = colors)",
        "androidx.compose.ui.unit.dp"
    )
)
@Composable
public fun KSlider(
    modifier: Modifier = Modifier,
    value: Float = 0f,
    minValue: Float = 0f,
    maxValue: Float = 1f,
    colors: KSliderColors = KSliderDefaults.colors(),
    onValueChanged: (Float) -> Unit = {}
): Unit = com.farimarwat.krossui.KSlider(
    value = value,
    onValueChange = onValueChanged,
    modifier = modifier,
    minValue = minValue,
    maxValue = maxValue,
    colors = colors
)
