package com.farimarwat.krossui

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.TextUnit
import com.farimarwat.krossui.utils.ButtonKind
import com.farimarwat.krossui.utils.applyNativeStyle
import com.farimarwat.krossui.utils.isIosAtLeast
import com.farimarwat.krossui.utils.toComposeColor
import com.farimarwat.krossui.utils.toUiColorOrNull
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue
import platform.UIKit.UIButton
import platform.UIKit.UIButtonTypeSystem

class NativeStyleTest {
    @Test
    fun unspecifiedColorMeansSystemDefault() = assertNull(Color.Unspecified.toUiColorOrNull())

    @Test
    fun colorRoundTripsThroughUiColor() {
        val color = Color(0xFF3366CC)
        assertEquals(color, assertNotNull(color.toUiColorOrNull()).toComposeColor())
    }

    @Test
    fun iosVersionCheck() {
        assertTrue(isIosAtLeast(14))
        assertFalse(isIosAtLeast(999))
    }

    @Test
    fun buttonUsesNativeConfiguration() {
        val button = UIButton.buttonWithType(UIButtonTypeSystem)
        button.applyNativeStyle(
            kind = ButtonKind.Filled,
            title = "Save",
            image = null,
            fontSize = TextUnit.Unspecified,
            containerColor = Color.Unspecified,
            contentColor = Color.Unspecified
        )
        val config = assertNotNull(button.configuration, "iOS 15+ buttons use a UIButtonConfiguration")
        assertEquals("Save", config.title)
        // Unspecified content color keeps the system foreground
        assertNull(config.baseForegroundColor)
    }

    @Test
    fun customColorsAreApplied() {
        val button = UIButton.buttonWithType(UIButtonTypeSystem)
        button.applyNativeStyle(
            kind = ButtonKind.Filled,
            title = "Save",
            image = null,
            fontSize = TextUnit.Unspecified,
            containerColor = Color.Red,
            contentColor = Color.White
        )
        assertEquals(Color.Red, button.tintColor.toComposeColor())
        assertEquals(Color.White, assertNotNull(button.configuration?.baseForegroundColor).toComposeColor())
    }
}
