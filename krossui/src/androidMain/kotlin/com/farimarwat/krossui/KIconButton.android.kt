package com.farimarwat.krossui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
public actual fun KIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    title: String?,
    iosIcon: String?, // Ignored on Android
    androidIcon: DrawableResource?,
    colors: KIconButtonColors,
    fontSize: TextUnit,
    cornerRadius: Dp
) {
    // Always use Button for consistency and proper icon+text support
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        shape = RoundedCornerShape(cornerRadius),
        colors = ButtonDefaults.buttonColors(
            containerColor = if (enabled) colors.containerColor else colors.disabledContainerColor,
            contentColor = if (enabled) colors.contentColor else colors.disabledContentColor,
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
