package com.farimarwat.krossuidemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.KTextField
import com.farimarwat.krossui.components.KButton
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import krossuidemo.composeapp.generated.resources.Res
import krossuidemo.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            var textValue  by remember { mutableStateOf("") }
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
                onClick = {
                    println("Clicked")
                }
            )
        }
    }
}