package com.farimarwat.krossui.components.KIconButton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import org.jetbrains.compose.resources.DrawableResource

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import org.jetbrains.compose.resources.painterResource

@Composable
actual fun KIconButton(
    modifier: Modifier,
    title: String?,
    onClick: () -> Unit,
    colors: KIconButtonColors,
    fontSize: TextUnit,
    cornerRadius: Double,
    isEnabled: Boolean,
    iosIcon: String?, // Ignored on Android
    androidIcon: DrawableResource?
) {
    // Always use Button for consistency and proper icon+text support
    Button(
        onClick = onClick,
        enabled = isEnabled,
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (isEnabled) colors.containerColor else colors.disabledContainerColor,
            contentColor = if (isEnabled) colors.contentColor else colors.disabledContentColor,
            disabledContainerColor = colors.disabledContainerColor,
            disabledContentColor = colors.disabledContentColor
        )
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            // Show icon if provided
            androidIcon?.let {
                Icon(
                    painter = painterResource(it),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                )
                // Add spacing only if we also have text
                if (!title.isNullOrBlank()) {
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }

            // Show text if provided
            if (!title.isNullOrBlank()) {
                Text(
                    text = title,
                    fontSize = fontSize
                )
            }
        }
    }
}