package com.farimarwat.krossuidemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.components.ColorUtils.PlatformColors
import com.farimarwat.krossui.components.Common.KPadding
import com.farimarwat.krossui.components.KTextField.KTextField
import com.farimarwat.krossui.components.KButton.KButton
import com.farimarwat.krossui.components.KDialog.KDialog
import com.farimarwat.krossui.components.KMenu.KMenu
import com.farimarwat.krossui.components.KMenu.KMenuItem
import com.farimarwat.krossui.components.KOutLinedButton.KOutLinedButton
import com.farimarwat.krossui.components.KSlider.KSlider
import com.farimarwat.krossui.components.KSwitch.KSwitch
import com.farimarwat.krossui.components.KTextField.KTextFieldDefaults
import com.farimarwat.krossui.components.KProgressIndicator.KProgressIndicator
import com.farimarwat.krossui.components.KTabBar.KTabBar
import com.farimarwat.krossui.components.KTabBar.KTabBarDefaults
import com.farimarwat.krossui.components.KTabBar.KTabItem
import krossuidemo.composeapp.generated.resources.Res
import krossuidemo.composeapp.generated.resources.ic_home
import krossuidemo.composeapp.generated.resources.ic_settings
import krossuidemo.composeapp.generated.resources.ic_star
import org.jetbrains.compose.ui.tooling.preview.Preview

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
                    .padding(8.dp)
                    .fillMaxSize()
                    .padding(paddingValues)
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                var textValue by remember { mutableStateOf("") }
                var switch by remember { mutableStateOf(true) }
                KTextField(
                    modifier = Modifier.fillMaxWidth()
                        .height(48.dp),
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
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ){
                    KButton(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(40.dp)
                            .weight(0.5f),
                        text = "Press Me",
                        onClick = {
                            showDialog = true
                            println(showDialog)
                        }
                    )
                    KOutLinedButton(
                        modifier = Modifier.fillMaxWidth()
                            .weight(0.5f)
                            .height(40.dp),
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
                KMenu(
                    title = "Menu",
                    items = list,
                    iOSIcon = "gear",
                    androidIcon = Res.drawable.ic_settings,
                    onItemClick = {

                    }
                )
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
            }
        }
    }
}