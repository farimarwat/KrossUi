package com.farimarwat.krossui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit

/**
 * A password field: a single-line native text field whose text is hidden
 * (Material 3 outlined text field with password masking on Android, secure UITextField on iOS).
 *
 * Same styling as [KTextField]; see it for the parameters.
 */
@Composable
public expect fun KSecureField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    placeholder: String = "",
    fontSize: TextUnit = TextUnit.Unspecified,
    imeAction: ImeAction = ImeAction.Done,
    onImeAction: () -> Unit = {},
    contentPadding: PaddingValues? = null,
    borderWidth: Dp = KTextFieldDefaults.BorderWidth,
    cornerRadius: Dp = KTextFieldDefaults.CornerRadius,
    colors: KTextFieldColors = KTextFieldDefaults.colors()
)
