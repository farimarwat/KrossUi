package com.farimarwat.krossui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
public actual fun KMenu(
    items: List<KMenuItem>,
    onItemClick: (KMenuItem) -> Unit,
    modifier: Modifier,
    title: String,
    iosIcon: String?, // Ignored on Android
    androidIcon: DrawableResource?,
    colors: KMenuColors
) {
    var showMenu by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        if (androidIcon != null) {
            IconButton(
                onClick = {
                    showMenu = !showMenu
                }
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(androidIcon),
                    contentDescription = ""
                )
            }
        } else {
            Button(
                onClick = { showMenu = !showMenu }
            ) {
                Text(title)
            }
        }

        // Menu
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { showMenu = false }
        ) {
            items.forEach { item ->
                DropdownMenuItem(
                    onClick = {
                        onItemClick(item)
                        showMenu = false
                    },
                    enabled = item.enabled,
                    text = { Text(item.title, color = colors.contentColor) },
                    leadingIcon = item.androidIcon?.let {
                        {
                            Icon(painter = painterResource(it), contentDescription = null)
                        }
                    }
                )
            }
        }
    }
}
