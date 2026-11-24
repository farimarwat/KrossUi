@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.Common

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

@Deprecated(
    "Use androidx.compose.foundation.layout.PaddingValues with KTextField(contentPadding = ...). See MIGRATION.md."
)
public data class KPadding(
    val leading: Double = 0.0,
    val trailing: Double = 0.0,
    val top: Double = 0.0,
    val bottom: Double = 0.0
) {
    public companion object {
        public fun all(value: Double): KPadding = KPadding(value, value, value, value)
        public fun horizontal(value: Double): KPadding = KPadding(value, value, 0.0, 0.0)
        public fun vertical(value: Double): KPadding = KPadding(0.0, 0.0, value, value)
        public fun symmetric(horizontal: Double = 0.0, vertical: Double = 0.0): KPadding =
            KPadding(horizontal, horizontal, vertical, vertical)
    }
}
