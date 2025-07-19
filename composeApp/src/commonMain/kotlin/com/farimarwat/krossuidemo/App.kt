package com.farimarwat.krossuidemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.farimarwat.krossui.components.KTextField
import com.farimarwat.krossui.components.KButton.KButton
import com.farimarwat.krossui.components.KButton.KButtonDefaults
import com.farimarwat.krossui.components.KDialog
import com.farimarwat.krossui.components.KSwitch
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
                onValueChange = {
                    textValue = it
                    println(it)
                }
            )
            KButton(
                modifier = Modifier.fillMaxWidth()
                    .height(48.dp),
                text = "Press Me",
                colors = KButtonDefaults.colors(
                    containerColor = Color.Red,
                    contentColor = Color.Black
                ),
                cornerRadius = 20.0,
                onClick = {
                    showDialog = true
                    println(showDialog)
                }
            )
            KSwitch(
                modifier = Modifier.width(100.dp)
                    .height(48.dp),
                checked = switch,
                onCheckedChange = {
                    switch = it
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