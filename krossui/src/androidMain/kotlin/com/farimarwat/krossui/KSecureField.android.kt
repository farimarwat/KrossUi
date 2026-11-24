package com.farimarwat.krossui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

@Composable
public actual fun KSecureField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    placeholder: String,
    fontSize: TextUnit,
    imeAction: ImeAction,
    onImeAction: () -> Unit,
    contentPadding: PaddingValues?,
    borderWidth: Dp,
    cornerRadius: Dp,
    colors: KTextFieldColors
): Unit = PlatformTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    enabled = enabled,
    readOnly = false,
    placeholder = placeholder,
    fontSize = fontSize,
    maxLines = 1,
    keyboardType = KeyboardType.Password,
    imeAction = imeAction,
    onImeAction = onImeAction,
    contentPadding = contentPadding,
    borderWidth = borderWidth,
    cornerRadius = cornerRadius,
    colors = colors,
    secure = true
)
