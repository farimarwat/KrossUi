package com.farimarwat.krossui

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

/**
 * Colors of a [KButton] in enabled and disabled states.
 *
 * @property containerColor The background of the enabled button.
 * @property contentColor The label color of the enabled button.
 * @property disabledContainerColor The background of the disabled button.
 * @property disabledContentColor The label color of the disabled button.
 */
@Immutable
public data class KButtonColors(
    val containerColor: Color,
    val contentColor: Color,
    val disabledContainerColor: Color,
    val disabledContentColor: Color
)
