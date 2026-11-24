package com.farimarwat.krossuidemo

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.KButton
import com.farimarwat.krossui.KDatePicker
import com.farimarwat.krossui.KDialog
import com.farimarwat.krossui.KIconButton
import com.farimarwat.krossui.KMenu
import com.farimarwat.krossui.KMenuItem
import com.farimarwat.krossui.KOutlinedButton
import com.farimarwat.krossui.KPicker
import com.farimarwat.krossui.KProgressIndicator
import com.farimarwat.krossui.KSecureField
import com.farimarwat.krossui.KSlider
import com.farimarwat.krossui.KSwitch
import com.farimarwat.krossui.KTabBar
import com.farimarwat.krossui.KTabItem
import com.farimarwat.krossui.KTextField
import krossuidemo.composeapp.generated.resources.Res
import krossuidemo.composeapp.generated.resources.ic_home
import krossuidemo.composeapp.generated.resources.ic_settings
import krossuidemo.composeapp.generated.resources.ic_star

@Composable
@Preview
fun App() {
    // Follow the system light/dark setting; KrossUI components pick up this theme on Android
    MaterialTheme(colorScheme = if (isSystemInDarkTheme()) darkColorScheme() else lightColorScheme()) {
        var showDialog by remember { mutableStateOf(false) }
        var selectedTab by remember { mutableStateOf(0) }
        val list = listOf(
            KTabItem("Components", Res.drawable.ic_home, "square.grid.2x2"),
            KTabItem("Layout", Res.drawable.ic_settings, "ruler")
        )
        Scaffold(
            bottomBar = {
                KTabBar(
                    tabs = list,
                    selectedIndex = selectedTab,
                    onTabClick = {
                        selectedTab = it
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        ) { paddingValues ->
            if (selectedTab == 1) {
                LayoutScreen(Modifier.fillMaxSize().padding(paddingValues))
                return@Scaffold
            }
            Column(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.background)
                    .padding(8.dp)
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                var textValue by remember { mutableStateOf("") }
                var switch by remember { mutableStateOf(true) }
                KTextField(
                    value = textValue,
                    onValueChange = { textValue = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Type something"
                )
                var password by remember { mutableStateOf("") }
                KSecureField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = "Password"
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        text = "Press Me",
                        onClick = {
                            showDialog = true
                            println(showDialog)
                        }
                    )
                    KOutlinedButton(
                        modifier = Modifier
                            .weight(0.5f),
                        text = "Help Me",
                        onClick = {
                            println("Working")
                        }
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        contentAlignment = Alignment.Center
                    ) {
                        KSwitch(
                            contentDescription = "Notifications",
                            checked = switch,
                            onCheckedChange = {
                                switch = it
                                println(it)
                            }
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        contentAlignment = Alignment.Center
                    ) {
                        KProgressIndicator(
                            contentDescription = "Loading",
                            size = 50.dp
                        )
                    }
                }
                var sliderValue by remember { mutableStateOf(0.5f) }
                KSlider(
                    contentDescription = "Volume",
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = sliderValue,
                    onValueChange = {
                        sliderValue = it
                        println("Slider Value: $it")
                    }
                )

                val list = listOf(
                    KMenuItem(
                        title = "Home sweet Home",
                        androidIcon = Res.drawable.ic_home,
                        iosIcon = "house"
                    ),
                    KMenuItem(
                        title = "Favourite",
                        androidIcon = Res.drawable.ic_star,
                        iosIcon = "star.fill"
                    ),
                    KMenuItem(
                        title = "Settings",
                        androidIcon = Res.drawable.ic_settings,
                        iosIcon = "gear"
                    )
                )
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        contentAlignment = Alignment.Center
                    ) {
                        KMenu(
                            contentDescription = "Settings menu",
                            title = "Menu",
                            items = list,
                            iosIcon = "gear",
                            androidIcon = Res.drawable.ic_settings,
                            onItemClick = {
                            }
                        )
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        contentAlignment = Alignment.Center
                    ) {
                        KIconButton(
                            modifier = Modifier
                                .fillMaxWidth(),
                            title = "Favourite",
                            iosIcon = "star.fill",
                            androidIcon = Res.drawable.ic_star,
                            onClick = {
                                println("Icon Button Pressed")
                            }
                        )
                    }
                }

                val fruits = listOf("Apple", "Mango", "Grapes", "Pineapple", "Banana")
                var fruitIndex by rememberSaveable { mutableStateOf(0) }
                KPicker(
                    contentDescription = "Fruit",
                    items = fruits,
                    selectedIndex = fruitIndex,
                    onSelectedIndexChange = { fruitIndex = it },
                    modifier = Modifier.fillMaxWidth()
                )
                var showDatePicker by remember { mutableStateOf(false) }
                var currentDate by remember { mutableStateOf("") }
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    KButton(
                        modifier = Modifier.fillMaxWidth()
                            .height(48.dp)
                            .weight(0.5f),
                        onClick = {
                            showDatePicker = true
                        },
                        text = "Date Picker"
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f),
                        text = currentDate,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                KDialog(
                    show = showDialog,
                    title = "My Dialog",
                    message = textValue,
                    confirmText = "OK",
                    cancelText = "Close",
                    onConfirm = {
                        showDialog = false
                    },
                    onCancel = {
                        showDialog = false
                    }
                )

                KDatePicker(
                    show = showDatePicker,
                    onDismiss = {
                        showDatePicker = false
                    },
                    onDateSelected = {
                        showDatePicker = false
                        currentDate = it.toString()
                    }
                )
            }
        }
    }
}
