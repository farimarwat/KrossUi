package com.farimarwat.krossui.components.Common

data class KPadding(
    val leading: Double = 0.0,
    val trailing: Double = 0.0,
    val top: Double = 0.0,
    val bottom: Double = 0.0
) {
    companion object {
        fun all(value: Double) = KPadding(value, value, value, value)
        fun horizontal(value: Double) = KPadding(value, value, 0.0, 0.0)
        fun vertical(value: Double) = KPadding(0.0, 0.0, value, value)
        fun symmetric(horizontal: Double = 0.0, vertical: Double = 0.0) = 
            KPadding(horizontal, horizontal, vertical, vertical)
    }
}