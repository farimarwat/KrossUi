package com.farimarwat.krossui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.semantics.ProgressBarRangeInfo
import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performSemanticsAction
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.datetime.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

/** Behaviour of the Android (Material 3) implementations, run on the JVM with Robolectric. */
@RunWith(AndroidJUnit4::class)
// A typical phone screen (Robolectric's default screen is very small)
@Config(qualifiers = "w411dp-h891dp-xxhdpi")
class ComponentInteractionTest {
    @get:Rule
    val compose = createComposeRule()

    @Test
    fun button_click_callsOnClick() {
        var clicks = 0
        compose.setContent { KButton(text = "Save", onClick = { clicks++ }) }
        compose.onNodeWithText("Save").performClick()
        assertEquals(1, clicks)
    }

    @Test
    fun button_disabled_isNotEnabled() {
        compose.setContent { KButton(text = "Save", onClick = {}, enabled = false) }
        compose.onNodeWithText("Save").assertIsNotEnabled()
    }

    @Test
    fun outlinedButton_click_callsOnClick() {
        var clicks = 0
        compose.setContent { KOutlinedButton(text = "Cancel", onClick = { clicks++ }) }
        compose.onNodeWithText("Cancel").performClick()
        assertEquals(1, clicks)
    }

    @Test
    fun iconButton_isFoundByContentDescription() {
        var clicks = 0
        compose.setContent { KIconButton(onClick = { clicks++ }, contentDescription = "Favourite") }
        compose.onNodeWithContentDescription("Favourite").performClick()
        assertEquals(1, clicks)
    }

    @Test
    fun switch_toggles() {
        compose.setContent {
            var checked by remember { mutableStateOf(false) }
            KSwitch(checked = checked, onCheckedChange = { checked = it }, contentDescription = "Wi-Fi")
        }
        val switch = compose.onNode(SemanticsMatcher.keyIsDefined(SemanticsProperties.ToggleableState))
        switch.assertIsOff()
        switch.performClick()
        switch.assertIsOn()
        compose.onNodeWithContentDescription("Wi-Fi", useUnmergedTree = true).assertIsDisplayed()
    }

    @Test
    fun slider_setProgress_reportsValue() {
        var value = 0f
        compose.setContent {
            var current by remember { mutableStateOf(0f) }
            KSlider(
                value = current,
                onValueChange = {
                    current = it
                    value = it
                },
                valueRange = 0f..10f,
                contentDescription = "Volume"
            )
        }
        compose.onNodeWithContentDescription("Volume")
            .assert(
                SemanticsMatcher.expectValue(
                    SemanticsProperties.ProgressBarRangeInfo,
                    ProgressBarRangeInfo(
                        0f,
                        0f..10f
                    )
                )
            )
            .performSemanticsAction(SemanticsActions.SetProgress) { it(7f) }
        assertEquals(7f, value)
    }

    @Test
    fun textField_typing_reportsText() {
        compose.setContent {
            var text by remember { mutableStateOf("") }
            KTextField(value = text, onValueChange = { text = it }, placeholder = "Name")
        }
        compose.onNode(SemanticsMatcher.keyIsDefined(SemanticsActions.SetText)).performTextInput("Ada")
        compose.onNodeWithText("Ada").assertIsDisplayed()
    }

    @Test
    fun secureField_isPassword() {
        compose.setContent { KSecureField(value = "secret", onValueChange = {}) }
        compose.onNode(SemanticsMatcher.keyIsDefined(SemanticsProperties.Password)).assertIsDisplayed()
    }

    @Test
    fun picker_selectingRow_reportsIndex() {
        var selected = -1
        compose.setContent {
            var index by remember { mutableStateOf(0) }
            KPicker(
                items = listOf("Apple", "Mango", "Grapes"),
                selectedIndex = index,
                onSelectedIndexChange = {
                    index = it
                    selected = it
                }
            )
        }
        compose.onNodeWithText("Mango").performClick()
        assertEquals(1, selected)
        compose.onNodeWithText("Mango").assertIsSelected()
    }

    @Test
    fun menu_opensAndReportsItem() {
        var picked: KMenuItem? = null
        val items = listOf(KMenuItem("Edit"), KMenuItem("Delete"))
        compose.setContent { KMenu(items = items, onItemClick = { picked = it }, title = "Actions") }
        compose.onNodeWithText("Actions").performClick()
        compose.onNodeWithText("Delete").performClick()
        assertEquals(items[1], picked)
    }

    @Test
    fun dialog_confirm_callsOnConfirm() {
        var confirmed = false
        compose.setContent {
            KDialog(show = true, title = "Delete?", message = "This cannot be undone.", onConfirm = {
                confirmed = true
            })
        }
        compose.onNodeWithText("Delete?").assertIsDisplayed()
        compose.onNodeWithText("OK").performClick()
        assertEquals(true, confirmed)
    }

    @Test
    fun datePicker_done_returnsInitialDate() {
        val date = LocalDate(2026, 3, 15)
        var picked: LocalDate? = null
        compose.setContent { KDatePicker(show = true, onDateSelected = { picked = it }, initialDate = date) }
        compose.onNodeWithText("Done").performClick()
        assertEquals(date, picked)
    }

    @Test
    fun progressIndicator_hasContentDescription() {
        compose.setContent { KProgressIndicator(contentDescription = "Loading") }
        compose.onNodeWithContentDescription("Loading").assertIsDisplayed()
    }
}
