package com.farimarwat.krossui.utils

import androidx.compose.ui.graphics.Color
import platform.UIKit.UIColor

fun Color.toUiColor(): UIColor {
    return UIColor(
        red = this.red.toDouble(),
        green = this.green.toDouble(),
        blue = this.blue.toDouble(),
        alpha = this.alpha.toDouble()
    )
}