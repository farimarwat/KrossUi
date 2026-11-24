package com.farimarwat.krossui

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.takeOrElse
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
    contentDescription: String?,
    colors: KMenuColors
) {
    var expanded by remember { mutableStateOf(false) }
    Box(modifier = modifier) {
        if (androidIcon != null) {
            IconButton(onClick = { expanded = true }) {
                Icon(
                    painter = painterResource(androidIcon),
                    contentDescription =
                        contentDescription ?: title.ifBlank { null }
                )
            }
        } else {
            TextButton(onClick = { expanded = true }) {
                Text(title)
            }
        }

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            containerColor = colors.containerColor.takeOrElse { MenuDefaults.containerColor }
        ) {
            val itemColors = MenuDefaults.itemColors(
                textColor = colors.contentColor,
                leadingIconColor = colors.contentColor,
                disabledTextColor = colors.disabledContentColor,
                disabledLeadingIconColor = colors.disabledContentColor
            )
            items.forEach { item ->
                DropdownMenuItem(
                    text = { Text(item.title) },
                    onClick = {
                        expanded = false
                        onItemClick(item)
                    },
                    enabled = item.enabled,
                    leadingIcon = item.androidIcon?.let { icon ->
                        { Icon(painter = painterResource(icon), contentDescription = null) }
                    },
                    colors = itemColors
                )
            }
        }
    }
}
