package com.farimarwat.krossuidemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.farimarwat.krossui.KButton
import com.farimarwat.krossui.KButtonDefaults
import com.farimarwat.krossui.KDatePicker
import com.farimarwat.krossui.KDialog
import com.farimarwat.krossui.KIconButton
import com.farimarwat.krossui.KMenu
import com.farimarwat.krossui.KMenuItem
import com.farimarwat.krossui.KOutlinedButton
import com.farimarwat.krossui.KOutlinedButtonDefaults
import com.farimarwat.krossui.KPicker
import com.farimarwat.krossui.KProgressIndicator
import com.farimarwat.krossui.KSecureField
import com.farimarwat.krossui.KSlider
import com.farimarwat.krossui.KSliderDefaults
import com.farimarwat.krossui.KSwitch
import com.farimarwat.krossui.KSwitchDefaults
import com.farimarwat.krossui.KTabBar
import com.farimarwat.krossui.KTabItem
import com.farimarwat.krossui.KTextField
import com.farimarwat.krossui.KTextFieldDefaults
import kotlin.math.roundToInt
import kotlinx.datetime.LocalDate
import krossuidemo.composeapp.generated.resources.Res
import krossuidemo.composeapp.generated.resources.ic_home
import krossuidemo.composeapp.generated.resources.ic_settings
import krossuidemo.composeapp.generated.resources.ic_star

@Composable
fun ButtonsPage(options: DemoOptions, log: EventLog) = DemoPage(log) {
    Section("Native default", "Android: Material 3. iOS 26: Liquid Glass; earlier iOS: filled / bordered.") {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            KButton(text = "Save", onClick = { log.log("KButton: onClick") }, enabled = options.enabled)
            KOutlinedButton(text = "Cancel", onClick = {
                log.log("KOutlinedButton: onClick")
            }, enabled = options.enabled)
        }
        KIconButton(
            onClick = { log.log("KIconButton (title): onClick") },
            enabled = options.enabled,
            title = "Favourite",
            iosIcon = "star.fill",
            androidIcon = Res.drawable.ic_star
        )
    }
    Section("Icon only", "Screen readers should say \"Favourite, button\" (not the icon name).") {
        KIconButton(
            onClick = { log.log("KIconButton (icon only): onClick") },
            enabled = options.enabled,
            iosIcon = "star.fill",
            androidIcon = Res.drawable.ic_star,
            contentDescription = "Favourite"
        )
    }
    Section("Custom colors", "Overrides win over the native look on both platforms.") {
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            KButton(
                text = "Delete",
                onClick = { log.log("KButton (red): onClick") },
                enabled = options.enabled,
                colors = KButtonDefaults.colors(containerColor = Color(0xFFD32F2F), contentColor = Color.White)
            )
            KOutlinedButton(
                text = "Approve",
                onClick = { log.log("KOutlinedButton (green): onClick") },
                enabled = options.enabled,
                colors = KOutlinedButtonDefaults.colors(
                    contentColor = Color(0xFF2E7D32),
                    borderColor = Color(0xFF2E7D32)
                )
            )
        }
    }
    Section("Custom size and shape", "Fills the width, larger text, small corners (shape ignored by iOS 26 glass).") {
        KButton(
            text = "Continue",
            onClick = { log.log("KButton (wide): onClick") },
            modifier = Modifier.fillMaxWidth(),
            enabled = options.enabled,
            fontSize = 20.sp,
            cornerRadius = 4.dp
        )
    }
}

@Composable
fun TextFieldsPage(options: DemoOptions, log: EventLog) = DemoPage(log) {
    var name by rememberSaveable { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    Section("Native default", "Type and check the text below updates; Done closes the keyboard.") {
        KTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            enabled = options.enabled,
            placeholder = "Name",
            imeAction = ImeAction.Done,
            onImeAction = { log.log("KTextField: onImeAction (Done)") }
        )
        StateText("value = \"$name\"")
    }
    Section("Number keyboard", "A numeric keyboard should open.") {
        KTextField(
            value = amount,
            onValueChange = { amount = it },
            modifier = Modifier.fillMaxWidth(),
            enabled = options.enabled,
            placeholder = "Amount",
            keyboardType = KeyboardType.Number
        )
    }
    Section("Custom style", "Thick border, rounded corners, extra padding, custom focus color.") {
        KTextField(
            value = name,
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth(),
            enabled = options.enabled,
            placeholder = "Styled",
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 14.dp),
            borderWidth = 2.dp,
            cornerRadius = 16.dp,
            colors = KTextFieldDefaults.colors(focusedBorderColor = Color(0xFF7B1FA2))
        )
    }
    Section("Read only", "Text can be selected but not edited.") {
        KTextField(value = "Read-only text", onValueChange = {}, modifier = Modifier.fillMaxWidth(), readOnly = true)
    }
    Section("KSecureField", "Characters are hidden.") {
        KSecureField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.fillMaxWidth(),
            enabled = options.enabled,
            placeholder = "Password",
            onImeAction = { log.log("KSecureField: onImeAction") }
        )
        StateText("length = ${password.length}")
    }
}

@Composable
fun SwitchPage(options: DemoOptions, log: EventLog) = DemoPage(log) {
    var wifi by rememberSaveable { mutableStateOf(true) }
    var custom by rememberSaveable { mutableStateOf(false) }
    Section("Native default", "Android: Material switch. iOS: system green UISwitch.") {
        Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            KSwitch(
                checked = wifi,
                onCheckedChange = {
                    wifi = it
                    log.log("KSwitch: onCheckedChange($it)")
                },
                enabled = options.enabled,
                contentDescription = "Wi-Fi"
            )
            Text("Wi-Fi")
        }
        StateText("checked = $wifi")
    }
    Section("Custom colors", "Orange track when on.") {
        KSwitch(
            checked = custom,
            onCheckedChange = {
                custom = it
                log.log("KSwitch (orange): onCheckedChange($it)")
            },
            enabled = options.enabled,
            colors = KSwitchDefaults.colors(trackColorChecked = Color(0xFFF57C00))
        )
    }
}

@Composable
fun SliderPage(options: DemoOptions, log: EventLog) = DemoPage(log) {
    var volume by rememberSaveable { mutableStateOf(0.4f) }
    var rating by rememberSaveable { mutableStateOf(50f) }
    Section("Continuous (0–1)", "Drag; the value updates live. Release logs onValueChangeFinished.") {
        KSlider(
            value = volume,
            onValueChange = { volume = it },
            enabled = options.enabled,
            onValueChangeFinished = { log.log("KSlider: finished at ${(volume * 100).roundToInt() / 100f}") },
            contentDescription = "Volume"
        )
        StateText("value = ${(volume * 100).roundToInt() / 100f}")
    }
    Section("Steps (0–100, 4 steps)", "Snaps to 0, 20, 40, 60, 80, 100 on both platforms.") {
        KSlider(
            value = rating,
            onValueChange = { rating = it },
            enabled = options.enabled,
            valueRange = 0f..100f,
            steps = 4,
            onValueChangeFinished = { log.log("KSlider (steps): finished at ${rating.roundToInt()}") }
        )
        StateText("value = ${rating.roundToInt()}")
    }
    Section("Custom colors", "Red active track.") {
        KSlider(
            value = volume,
            onValueChange = { volume = it },
            enabled = options.enabled,
            colors = KSliderDefaults.colors(activeTrackColor = Color(0xFFD32F2F))
        )
    }
}

@Composable
fun PickerPage(log: EventLog) = DemoPage(log) {
    val fruits = listOf("Apple", "Mango", "Grapes", "Pineapple", "Banana", "Cherry")
    var index by rememberSaveable { mutableStateOf(1) }
    Section("Wheel picker", "Scroll or tap; the selection below updates. iOS: native wheel.") {
        KPicker(
            items = fruits,
            selectedIndex = index,
            onSelectedIndexChange = {
                index = it
                log.log("KPicker: onSelectedIndexChange($it = ${fruits[it]})")
            },
            modifier = Modifier.fillMaxWidth(),
            contentDescription = "Fruit"
        )
        StateText("selected = ${fruits[index]} (#$index)")
    }
    Section("Controlled by the app", "Changing the index from code moves the wheel.") {
        KButton(text = "Next fruit", onClick = { index = (index + 1) % fruits.size })
    }
}

@Composable
fun DatePickerPage(log: EventLog) = DemoPage(log) {
    var show by remember { mutableStateOf(false) }
    var picked by remember { mutableStateOf<LocalDate?>(null) }
    Section("Date picker", "Opens on the last picked date (or today). Done returns it; Cancel logs a dismiss.") {
        KButton(text = "Pick a date", onClick = { show = true })
        StateText("picked = ${picked ?: "—"}")
    }
    KDatePicker(
        show = show,
        onDateSelected = {
            show = false
            picked = it
            log.log("KDatePicker: onDateSelected($it)")
        },
        onDismiss = {
            show = false
            log.log("KDatePicker: onDismiss")
        },
        initialDate = picked
    )
}

@Composable
fun MenuPage(log: EventLog) = DemoPage(log) {
    val items = listOf(
        KMenuItem("Edit", androidIcon = Res.drawable.ic_settings, iosIcon = "pencil"),
        KMenuItem("Favourite", androidIcon = Res.drawable.ic_star, iosIcon = "star"),
        KMenuItem("Delete (disabled)", enabled = false)
    )
    Section("Text menu", "Tap to open; the third item is disabled.") {
        KMenu(items = items, onItemClick = { log.log("KMenu: onItemClick(${it.title})") }, title = "Actions")
    }
    Section("Icon menu", "Screen readers should say \"More options\".") {
        KMenu(
            items = items,
            onItemClick = { log.log("KMenu (icon): onItemClick(${it.title})") },
            iosIcon = "ellipsis.circle",
            androidIcon = Res.drawable.ic_settings,
            contentDescription = "More options"
        )
    }
}

@Composable
fun DialogPage(log: EventLog) = DemoPage(log) {
    var showInfo by remember { mutableStateOf(false) }
    var showConfirm by remember { mutableStateOf(false) }
    Section("Alert", "One button. iOS: UIAlertController; Android: Material AlertDialog.") {
        KButton(text = "Show alert", onClick = { showInfo = true })
    }
    Section("Confirmation", "OK and Cancel each log their callback, then onDismiss.") {
        KButton(text = "Delete item…", onClick = { showConfirm = true })
    }
    KDialog(
        show = showInfo,
        title = "Saved",
        message = "Your changes were saved.",
        onConfirm = {
            showInfo = false
            log.log("KDialog: onConfirm")
        },
        onDismiss = {
            showInfo = false
            log.log("KDialog: onDismiss")
        }
    )
    KDialog(
        show = showConfirm,
        title = "Delete item?",
        message = "This cannot be undone.",
        onConfirm = {
            showConfirm = false
            log.log("KDialog (confirm): onConfirm")
        },
        confirmText = "Delete",
        cancelText = "Cancel",
        onCancel = {
            showConfirm = false
            log.log("KDialog (confirm): onCancel")
        },
        onDismiss = {
            showConfirm = false
            log.log("KDialog (confirm): onDismiss")
        }
    )
}

@Composable
fun ProgressPage(log: EventLog) = DemoPage(log) {
    Section("Spinners", "Native default size, a larger one, and a custom color.") {
        Row(horizontalArrangement = Arrangement.spacedBy(32.dp), verticalAlignment = Alignment.CenterVertically) {
            KProgressIndicator(contentDescription = "Loading")
            KProgressIndicator(size = 48.dp)
            KProgressIndicator(color = Color(0xFF2E7D32))
        }
    }
}

@Composable
fun TabBarPage(log: EventLog) = DemoPage(log) {
    var selected by rememberSaveable { mutableStateOf(0) }
    val tabs = listOf(
        KTabItem("Home", Res.drawable.ic_home, "house.fill"),
        KTabItem("Favourites", Res.drawable.ic_star, "star.fill"),
        KTabItem("Settings", Res.drawable.ic_settings, "gearshape.fill")
    )
    Section("Tab bar", "Tap tabs; the selection follows. iOS 26: floating glass bar.") {
        KTabBar(
            tabs = tabs,
            selectedIndex = selected,
            onTabClick = {
                selected = it
                log.log("KTabBar: onTabClick($it = ${tabs[it].title})")
            },
            modifier = Modifier.fillMaxWidth()
        )
        StateText("selected = ${tabs[selected].title}")
    }
}
