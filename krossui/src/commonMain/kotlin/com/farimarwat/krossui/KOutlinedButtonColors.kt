package com.farimarwat.krossui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Colors of a [KOutlinedButton] in enabled and disabled states.
 *
 * @property containerColor The background of the enabled button.
 * @property contentColor The label color of the enabled button.
 * @property disabledContainerColor The background of the disabled button.
 * @property disabledContentColor The label color of the disabled button.
 * @property borderColor The border color of the enabled button.
 * @property disabledBorderColor The border color of the disabled button.
 */
@Immutable
public data class KOutlinedButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color,
    val borderColor: Color,
    val disabledBorderColor: Color
)
