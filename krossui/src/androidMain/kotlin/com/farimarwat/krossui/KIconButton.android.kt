package com.farimarwat.krossui

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.isSpecified
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
    val hasTitle = !title.isNullOrBlank()
    Button(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        shape = if (cornerRadius.isSpecified) RoundedCornerShape(cornerRadius) else ButtonDefaults.shape,
        colors = ButtonDefaults.buttonColors(
            containerColor = colors.containerColor,
            contentColor = colors.contentColor,
            disabledContainerColor = colors.disabledContainerColor,
            disabledContentColor = colors.disabledContentColor
        ),
        // Material's layout for buttons with a leading icon
        contentPadding = if (androidIcon != null && hasTitle) {
            ButtonDefaults.ButtonWithIconContentPadding
        } else {
            ButtonDefaults.ContentPadding
        }
    ) {
        androidIcon?.let {
            Icon(
                painter = painterResource(it),
                contentDescription = null,
                modifier = Modifier.size(ButtonDefaults.IconSize)
            )
            if (hasTitle) Spacer(Modifier.width(ButtonDefaults.IconSpacing))
        }
        if (hasTitle) Text(text = title, fontSize = fontSize)
    }
}
