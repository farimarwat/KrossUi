@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KTextField

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.PlatformColors
import com.farimarwat.krossui.components.Common.KPadding

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KTextFieldColors")
)
public typealias KTextFieldColors = com.farimarwat.krossui.KTextFieldColors

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith("com.farimarwat.krossui.KTextFieldDefaults")
)
public object KTextFieldDefaults {
    @Composable
    public fun colors(
        textColor: Color = Color.Black,
        unfocusedBackgroundColor: Color = Color.White,
        focusedBackgroundColor: Color = Color.White,
        placeholderColor: Color = Color.Gray,
        unfocusedBorderColor: Color = Color.LightGray,
        focusedBorderColor: Color = PlatformColors.systemBlue,
        disabledTextColor: Color = Color.Gray,
        disabledBorderColor: Color = Color.Gray,
        readOnlyTextColor: Color = Color.DarkGray
    ): KTextFieldColors = com.farimarwat.krossui.KTextFieldDefaults.colors(
        textColor = textColor,
        unfocusedBackgroundColor = unfocusedBackgroundColor,
        focusedBackgroundColor = focusedBackgroundColor,
        placeholderColor = placeholderColor,
        unfocusedBorderColor = unfocusedBorderColor,
        focusedBorderColor = focusedBorderColor,
        disabledTextColor = disabledTextColor,
        disabledBorderColor = disabledBorderColor,
        readOnlyTextColor = readOnlyTextColor
    )

    public val BorderWidth: Int = 1
    public val CornerRadius: Double = 8.0
    public val Padding: Int = 12
}

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KTextField(value = value, onValueChange = onValueChange, modifier = modifier, enabled = isEnabled, readOnly = isReadOnly, placeholder = placeholder, fontSize = fontSize, maxLines = maxLines, keyboardType = keyboardType, imeAction = imeAction, borderWidth = borderWidth.dp, cornerRadius = cornerRadius.dp, colors = colors)",
        "androidx.compose.ui.unit.dp"
    )
)
@Composable
public fun KTextField(
    modifier: Modifier = Modifier,
    padding: KPadding = KPadding.all(12.0),
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    fontSize: TextUnit = TextUnit.Unspecified,
    isEnabled: Boolean = true,
    isReadOnly: Boolean = false,
    maxLines: Int = 1,
    borderWidth: Int = KTextFieldDefaults.BorderWidth,
    cornerRadius: Double = KTextFieldDefaults.CornerRadius,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Default,
    colors: KTextFieldColors = KTextFieldDefaults.colors()
): Unit = com.farimarwat.krossui.KTextField(
    value = value,
    onValueChange = onValueChange,
    modifier = modifier,
    enabled = isEnabled,
    readOnly = isReadOnly,
    placeholder = placeholder,
    fontSize = fontSize,
    maxLines = maxLines,
    keyboardType = keyboardType,
    imeAction = imeAction,
    contentPadding = PaddingValues(
        start = padding.leading.dp,
        top = padding.top.dp,
        end = padding.trailing.dp,
        bottom = padding.bottom.dp
    ),
    borderWidth = borderWidth.dp,
    cornerRadius = cornerRadius.dp,
    colors = colors
)
