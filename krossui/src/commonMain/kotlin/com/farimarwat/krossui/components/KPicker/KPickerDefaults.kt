package com.farimarwat.krossui.components.KPicker

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

/**
 * Provides default styling values for [KPicker].
 */
object KPickerDefaults {

    /**
     * Returns default color values for the [KPicker] component.
     *
     * @return [KPickerColors] with predefined platform-aware colors.
     */
    @Composable
    fun colors(): KPickerColors = KPickerColors(
        containerColor = Color.White,
        contentColor = PlatformColors.systemBlue,
        disabledContainerColor = PlatformColors.systemGray.copy(alpha = 0.5f),
        disabledContentColor = PlatformColors.systemGray
    )
}