@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KProgressIndicator

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.PlatformColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KProgressIndicatorColors")
)
public typealias KProgressIndicatorColors = com.farimarwat.krossui.KProgressIndicatorColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KProgressIndicatorDefaults")
)
public object KProgressIndicatorDefaults {
    public fun colors(color: Color = PlatformColors.systemBlue): KProgressIndicatorColors =
        KProgressIndicatorColors(color)

    public val DefaultSize: Double = 36.0
}

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KProgressIndicator(size = size.dp, color = color)",
        "androidx.compose.ui.unit.dp"
    )
)
@Composable
public fun KProgressIndicator(
    size: Double = KProgressIndicatorDefaults.DefaultSize,
    color: Color = KProgressIndicatorDefaults.colors().color
): Unit = com.farimarwat.krossui.KProgressIndicator(size = size.dp, color = color)
