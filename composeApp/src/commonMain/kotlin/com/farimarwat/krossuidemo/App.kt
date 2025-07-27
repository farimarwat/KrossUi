package com.farimarwat.krossuidemo

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.rememberScrollableState
import androidx.compose.foundation.gestures.scrollable
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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.components.ColorUtils.PlatformColors
import com.farimarwat.krossui.components.Common.KPadding
import com.farimarwat.krossui.components.KTextField.KTextField
import com.farimarwat.krossui.components.KButton.KButton
import com.farimarwat.krossui.components.KDatePicker.KDatePicker
import com.farimarwat.krossui.components.KDatePicker.KDatePickerColors
import com.farimarwat.krossui.components.KDatePicker.KDatePickerDefaults
import com.farimarwat.krossui.components.KDialog.KDialog
import com.farimarwat.krossui.components.KIconButton.KIconButton
import com.farimarwat.krossui.components.KMenu.KMenu
import com.farimarwat.krossui.components.KMenu.KMenuItem
import com.farimarwat.krossui.components.KOutLinedButton.KOutLinedButton
import com.farimarwat.krossui.components.KPicker.KPicker
import com.farimarwat.krossui.components.KSlider.KSlider
import com.farimarwat.krossui.components.KSwitch.KSwitch
import com.farimarwat.krossui.components.KTextField.KTextFieldDefaults
import com.farimarwat.krossui.components.KProgressIndicator.KProgressIndicator
import com.farimarwat.krossui.components.KTabBar.KTabBar
import com.farimarwat.krossui.components.KTabBar.KTabBarDefaults
import com.farimarwat.krossui.components.KTabBar.KTabItem
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import krossuidemo.composeapp.generated.resources.Res
import krossuidemo.composeapp.generated.resources.ic_home
import krossuidemo.composeapp.generated.resources.ic_settings
import krossuidemo.composeapp.generated.resources.ic_star
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.time.Clock
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

@OptIn(ExperimentalTime::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var showDialog by remember { mutableStateOf(false) }
        var selectedTab by remember { mutableStateOf(1)}
        val list = listOf(
            KTabItem(
                "Home",
                Res.drawable.ic_home,
                "house.fill"
            ),
            KTabItem(
                "Favourite",
                Res.drawable.ic_star,
                "star.fill"
            ),
            KTabItem(
                "Settings",
                Res.drawable.ic_settings,
                "gearshape.fill"
            )
        )
        Scaffold(
            bottomBar = {
                KTabBar(
                    modifier = Modifier.fillMaxWidth(),
                    list,
                    selectedTab,
                    onTabClick = {
                        selectedTab = it
                    },
                    colors = KTabBarDefaults.colors(
                        selectedContentColor = PlatformColors.systemRed,
                        unselectedContentColor = PlatformColors.systemGray
                    )
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .background(PlatformColors.systemBackground)
                    .padding(8.dp)
                    .fillMaxSize()
                    .padding(paddingValues)
                    .verticalScroll(rememberScrollState())
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                var textValue by remember { mutableStateOf("") }
                var switch by remember { mutableStateOf(true) }
                KTextField(
                    modifier = Modifier.fillMaxWidth()
                        ,
                    value = textValue,
                    cornerRadius = 8.0,
                    borderWidth = 1,
                    onValueChange = {
                        textValue = it
                        println(it)
                    },
                    padding = KPadding.horizontal(12.0),
                    colors = KTextFieldDefaults.colors(
                        focusedBackgroundColor = Color.LightGray
                    )
                )
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ){
                    KButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(0.5f)
                            ,
                        text = "Press Me",
                        onClick = {
                            showDialog = true
                            println(showDialog)
                        }
                    )
                    KOutLinedButton(
                        modifier = Modifier
                            .weight(0.5f),
                        text = "Help Me",
                        onClick = {

                        }
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    KSwitch(
                        modifier = Modifier
                            .width(100.dp)
                            .height(48.dp),
                        checked = switch,
                        onCheckedChange = {
                            switch = it
                        }
                    )

                    KProgressIndicator(
                        size = 50.0
                    )

                }
                var sliderValue by remember { mutableStateOf(0.5f)}
                KSlider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp),
                    value = sliderValue,
                    onValueChanged = {
                        sliderValue = it
                        println("Slider Value: ${it}")
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
               ){
                   Box(
                       modifier = Modifier
                           .fillMaxWidth()
                           .weight(0.5f),
                       contentAlignment = Alignment.Center
                   ){
                       KMenu(
                           title = "Menu",
                           items = list,
                           iOSIcon = "gear",
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
                   ){
                       KIconButton(
                           modifier = Modifier
                               .wrapContentWidth()
                               .height(40.dp),
                           title = "Favourite",
                           iosIcon = "star.fill",
                           androidIcon = Res.drawable.ic_star,
                           onClick = {
                               println("Icon Button Pressed")
                           }
                       )
                   }
               }

                KPicker(
                    modifier = Modifier
                        .fillMaxWidth(),
                    items = listOf("Apple","Mango","Graps","PineApple","Banana"),
                    onItemPicked = {
                        println(it)
                    }
                )
                var showDatePicker by remember { mutableStateOf(false)}
                var currentDate by remember { mutableStateOf("")}
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ){
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
                if(showDialog){
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
                }

                if(showDatePicker){
                    KDatePicker(
                        show = showDatePicker,
                        initialDate = Clock.System.now().toEpochMilliseconds(),
                        colors = KDatePickerDefaults.colors(
                            containerColor = PlatformColors.systemBackground
                        ),
                        onDismiss = {
                            showDatePicker = false
                        },
                        onDateSelected = {
                            showDatePicker = false
                            currentDate = formatDateFromMillis(it)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalTime::class)
fun formatDateFromMillis(timestamp: Long): String {
    val instant = Instant.fromEpochMilliseconds(timestamp)
    return instant.toString()
}