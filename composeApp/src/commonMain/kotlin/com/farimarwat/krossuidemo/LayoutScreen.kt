package com.farimarwat.krossuidemo

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.KButton
import com.farimarwat.krossui.KIconButton
import com.farimarwat.krossui.KMenu
import com.farimarwat.krossui.KMenuItem
import com.farimarwat.krossui.KOutlinedButton
import com.farimarwat.krossui.KProgressIndicator
import com.farimarwat.krossui.KSlider
import com.farimarwat.krossui.KSwitch
import com.farimarwat.krossui.KTextField
import krossuidemo.composeapp.generated.resources.Res
import krossuidemo.composeapp.generated.resources.ic_star

/**
 * Layout test page: every component wrapped (natural size), filling the width, and at a fixed size.
 * The tinted background shows each component's bounds.
 */
@Composable
fun LayoutScreen(modifier: Modifier = Modifier) {
    var checked by remember { mutableStateOf(true) }
    var slider by remember { mutableStateOf(0.4f) }
    var text by remember { mutableStateOf("") }
    val bounds = Modifier.background(MaterialTheme.colorScheme.primary.copy(alpha = 0.12f))

    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Section("KButton") {
            KButton(text = "Wrap", onClick = {}, modifier = bounds)
            KButton(text = "Fill width", onClick = {}, modifier = bounds.fillMaxWidth())
            KButton(text = "Fixed 220×56", onClick = {}, modifier = bounds.size(220.dp, 56.dp))
        }
        Section("KOutlinedButton") {
            KOutlinedButton(text = "Wrap", onClick = {}, modifier = bounds)
            KOutlinedButton(text = "Fill width", onClick = {}, modifier = bounds.fillMaxWidth())
        }
        Section("KIconButton") {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                KIconButton(
                    onClick = {},
                    modifier = bounds,
                    iosIcon = "star.fill",
                    androidIcon = Res.drawable.ic_star,
                    contentDescription = "Favourite"
                )
                KIconButton(
                    onClick = {},
                    modifier = bounds,
                    title = "With title",
                    iosIcon = "star.fill",
                    androidIcon = Res.drawable.ic_star
                )
            }
        }
        Section("KSwitch") {
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp), verticalAlignment = Alignment.CenterVertically) {
                KSwitch(checked = checked, onCheckedChange = { checked = it }, modifier = bounds)
                KSwitch(checked = checked, onCheckedChange = { checked = it }, enabled = false, modifier = bounds)
            }
        }
        Section("KSlider") {
            KSlider(value = slider, onValueChange = { slider = it }, modifier = bounds)
            KSlider(value = slider, onValueChange = { slider = it }, modifier = bounds.width(200.dp))
        }
        Section("KTextField") {
            KTextField(value = text, onValueChange = { text = it }, placeholder = "Wrap", modifier = bounds)
            KTextField(value = text, onValueChange = {
                text = it
            }, placeholder = "Fill width", modifier = bounds.fillMaxWidth())
        }
        Section("KProgressIndicator") {
            Row(horizontalArrangement = Arrangement.spacedBy(24.dp), verticalAlignment = Alignment.CenterVertically) {
                KProgressIndicator(modifier = bounds)
                KProgressIndicator(modifier = bounds, size = 48.dp)
            }
        }
        Section("KMenu") {
            KMenu(
                items = listOf(KMenuItem("One"), KMenuItem("Two")),
                onItemClick = {},
                modifier = bounds,
                title = "Open menu"
            )
        }
    }
}

@Composable
private fun Section(title: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(title, style = MaterialTheme.typography.titleSmall, color = MaterialTheme.colorScheme.onBackground)
        content()
    }
}
