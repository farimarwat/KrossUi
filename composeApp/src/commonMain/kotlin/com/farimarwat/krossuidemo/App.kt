package com.farimarwat.krossuidemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.components.ColorUtils.PlatformColors
import com.farimarwat.krossui.components.Common.KPadding
import com.farimarwat.krossui.components.KTextField.KTextField
import com.farimarwat.krossui.components.KButton.KButton
import com.farimarwat.krossui.components.KDialog
import com.farimarwat.krossui.components.KOutLinedButton.KOutLinedButton
import com.farimarwat.krossui.components.KSlider.KSlider
import com.farimarwat.krossui.components.KSwitch
import com.farimarwat.krossui.components.KTextField.KTextFieldDefaults
import com.farimarwat.krossui.components.KrossTab.KrossTabBar
import com.farimarwat.krossui.components.KrossTab.KrossTabBarDefaults
import com.farimarwat.krossui.components.KrossTab.KrossTabItem
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
            KrossTabItem(
                "Home",
                Res.drawable.ic_home,
                "house.fill"
            ),
            KrossTabItem(
                "Favourite",
                Res.drawable.ic_star,
                "star.fill"
            ),
            KrossTabItem(
                "Settings",
                Res.drawable.ic_settings,
                "gearshape.fill"
            )
        )
        Scaffold(
            bottomBar = {
                KrossTabBar(
                    modifier = Modifier.fillMaxWidth(),
                    list,
                    selectedTab,
                    onTabClick = {
                        selectedTab = it
                    },
                    colors = KrossTabBarDefaults.colors(
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
                        modifier = Modifier.fillMaxWidth()
                            .weight(0.5f)
                            .height(48.dp),
                        text = "Press Me",
                        onClick = {
                            showDialog = true
                            println(showDialog)
                        }
                    )
                    KOutLinedButton(
                        modifier = Modifier.fillMaxWidth()
                            .weight(0.5f)
                            .height(48.dp),
                        text = "Help Me",
                        onClick = {

                        }
                    )
                }
                KSwitch(
                    modifier = Modifier.width(100.dp)
                        .height(48.dp),
                    checked = switch,
                    onCheckedChange = {
                        switch = it
                    }

                )

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