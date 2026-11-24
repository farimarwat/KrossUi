package com.farimarwat.krossui

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.isSpecified
import androidx.compose.ui.unit.takeOrElse

/**
 * Material 3 outlined text field, built from Material's own pieces (BasicTextField + DecorationBox + Container)
 * so padding, border width and shape can be customized while keeping the native look.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
public actual fun KTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    readOnly: Boolean,
    placeholder: String,
    fontSize: TextUnit,
    maxLines: Int,
    keyboardType: KeyboardType,
    imeAction: ImeAction,
    contentPadding: PaddingValues?,
    borderWidth: Dp,
    cornerRadius: Dp,
    colors: KTextFieldColors
) {
    val interactionSource = remember { MutableInteractionSource() }
    val focused by interactionSource.collectIsFocusedAsState()
    val singleLine = maxLines == 1
    val textColor = if (readOnly) colors.readOnlyTextColor.takeOrElse { colors.textColor } else colors.textColor

    // Unspecified colors fall back to the app's MaterialTheme inside Material's defaults
    val fieldColors = OutlinedTextFieldDefaults.colors(
        focusedTextColor = textColor,
        unfocusedTextColor = textColor,
        disabledTextColor = colors.disabledTextColor,
        focusedContainerColor = colors.focusedBackgroundColor,
        unfocusedContainerColor = colors.unfocusedBackgroundColor,
        disabledContainerColor = colors.unfocusedBackgroundColor,
        focusedBorderColor = colors.focusedBorderColor,
        unfocusedBorderColor = colors.unfocusedBorderColor,
        disabledBorderColor = colors.disabledBorderColor,
        focusedPlaceholderColor = colors.placeholderColor,
        unfocusedPlaceholderColor = colors.placeholderColor
    )
    val resolvedTextColor = when {
        !enabled -> fieldColors.disabledTextColor
        focused -> fieldColors.focusedTextColor
        else -> fieldColors.unfocusedTextColor
    }
    val visualTransformation =
        if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None
    val shape = if (cornerRadius.isSpecified) RoundedCornerShape(cornerRadius) else OutlinedTextFieldDefaults.shape

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.defaultMinSize(
            minWidth = OutlinedTextFieldDefaults.MinWidth,
            minHeight = OutlinedTextFieldDefaults.MinHeight
        ),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = LocalTextStyle.current.merge(TextStyle(color = resolvedTextColor, fontSize = fontSize)),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        singleLine = singleLine,
        maxLines = maxLines,
        visualTransformation = visualTransformation,
        interactionSource = interactionSource,
        cursorBrush = SolidColor(fieldColors.cursorColor),
        decorationBox = { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = value,
                innerTextField = innerTextField,
                enabled = enabled,
                singleLine = singleLine,
                visualTransformation = visualTransformation,
                interactionSource = interactionSource,
                placeholder = if (placeholder.isNotEmpty()) {
                    { Text(text = placeholder, fontSize = fontSize) }
                } else {
                    null
                },
                colors = fieldColors,
                contentPadding = contentPadding ?: OutlinedTextFieldDefaults.contentPadding(),
                container = {
                    OutlinedTextFieldDefaults.Container(
                        enabled = enabled,
                        isError = false,
                        interactionSource = interactionSource,
                        colors = fieldColors,
                        shape = shape,
                        focusedBorderThickness = borderWidth.takeOrElse {
                            OutlinedTextFieldDefaults.FocusedBorderThickness
                        },
                        unfocusedBorderThickness = borderWidth.takeOrElse {
                            OutlinedTextFieldDefaults.UnfocusedBorderThickness
                        }
                    )
                }
            )
        }
    )
}
