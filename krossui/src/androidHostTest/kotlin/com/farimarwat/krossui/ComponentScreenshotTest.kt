package com.farimarwat.krossui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.captureRoboImage
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import org.robolectric.annotation.GraphicsMode

/**
 * Screenshots of every component (Android / Material 3), compared against `krossui/screenshots`.
 * Record:  ./gradlew :krossui:recordRoborazziAndroidHostTest
 * Verify:  ./gradlew :krossui:verifyRoborazziAndroidHostTest
 */
@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
@Config(qualifiers = "w411dp-h891dp-xxhdpi")
class ComponentScreenshotTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun gallery_light() = capture("gallery_light") { Gallery() }

    @Test
    fun gallery_dark() = capture("gallery_dark", dark = true) { Gallery() }

    @Test
    fun gallery_rtl() = capture("gallery_rtl", rtl = true) { Gallery() }

    @Test
    fun gallery_disabled() = capture("gallery_disabled") { Gallery(enabled = false) }

    private fun capture(name: String, dark: Boolean = false, rtl: Boolean = false, content: @Composable () -> Unit) {
        // Freeze animations (spinner, picker scroll) so every run captures the same frame
        compose.mainClock.autoAdvance = false
        compose.setContent {
            MaterialTheme(colorScheme = if (dark) darkColorScheme() else lightColorScheme()) {
                val direction = if (rtl) LayoutDirection.Rtl else LayoutDirection.Ltr
                CompositionLocalProvider(LocalLayoutDirection provides direction) {
                    Column(Modifier.background(MaterialTheme.colorScheme.background)) { content() }
                }
            }
        }
        compose.onRoot().captureRoboImage("screenshots/$name.png")
    }
}

@Composable
private fun Gallery(enabled: Boolean = true) {
    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        KTextField(value = "", onValueChange = {
        }, placeholder = "Name", enabled = enabled, modifier = Modifier.fillMaxWidth())
        KSecureField(value = "secret", onValueChange = {}, enabled = enabled, modifier = Modifier.fillMaxWidth())
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            KButton(text = "Save", onClick = {}, enabled = enabled)
            KOutlinedButton(text = "Cancel", onClick = {}, enabled = enabled)
            KIconButton(onClick = {}, title = "Star", enabled = enabled)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            KSwitch(checked = true, onCheckedChange = {}, enabled = enabled)
            KSwitch(checked = false, onCheckedChange = {}, enabled = enabled)
            KProgressIndicator()
        }
        KSlider(value = 0.3f, onValueChange = {}, enabled = enabled)
        KPicker(items = listOf("Apple", "Mango", "Grapes"), selectedIndex = 1, onSelectedIndexChange = {})
        KMenu(items = listOf(KMenuItem("Edit")), onItemClick = {}, title = "Menu")
    }
}
