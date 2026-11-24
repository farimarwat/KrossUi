package com.farimarwat.krossui.components.Common

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