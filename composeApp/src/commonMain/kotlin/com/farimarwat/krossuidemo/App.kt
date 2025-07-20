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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.components.Common.KPadding
import com.farimarwat.krossui.components.KTextField.KTextField
import com.farimarwat.krossui.components.KButton.KButton
import com.farimarwat.krossui.components.KDialog
import com.farimarwat.krossui.components.KOutLinedButton.KOutLinedButton
import com.farimarwat.krossui.components.KSlider.KSlider
import com.farimarwat.krossui.components.KSwitch
import com.farimarwat.krossui.components.KTextField.KTextFieldDefaults
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showDialog by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize()
                .padding(32.dp),
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