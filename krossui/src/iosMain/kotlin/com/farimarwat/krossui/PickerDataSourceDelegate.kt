package com.farimarwat.krossui

import com.farimarwat.krossui.utils.toUiColorOrNull
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCSignatureOverride
import platform.Foundation.NSAttributedString
import platform.Foundation.create
import platform.UIKit.NSForegroundColorAttributeName
import platform.UIKit.UIColor
import platform.UIKit.UIPickerView
import platform.UIKit.UIPickerViewDataSourceProtocol
import platform.UIKit.UIPickerViewDelegateProtocol
import platform.UIKit.labelColor
import platform.darwin.NSObject

/**
 * Data source and delegate for the iOS [KPicker].
 *
 * [items], [colors] and [onItemPicked] are mutable so the composable can keep them
 * in sync with the latest values on every recomposition without recreating the picker.
 */
@OptIn(ExperimentalForeignApi::class)
internal class PickerDataSourceDelegate(
    var items: List<String>,
    var colors: KPickerColors,
    var onItemPicked: (String) -> Unit
) : NSObject(),
    UIPickerViewDataSourceProtocol,
    UIPickerViewDelegateProtocol {

    override fun numberOfComponentsInPickerView(pickerView: UIPickerView): Long = 1L

    override fun pickerView(pickerView: UIPickerView, numberOfRowsInComponent: Long): Long = items.size.toLong()

    @ObjCSignatureOverride
    override fun pickerView(
        pickerView: UIPickerView,
        attributedTitleForRow: Long,
        forComponent: Long
    ): NSAttributedString? {
        val title = items.getOrNull(attributedTitleForRow.toInt()) ?: return null
        return NSAttributedString.create(
            string = title,
            attributes = mapOf<Any?, Any?>(
                NSForegroundColorAttributeName to (colors.contentColor.toUiColorOrNull() ?: UIColor.labelColor)
            )
        )
    }

    @ObjCSignatureOverride
    override fun pickerView(pickerView: UIPickerView, didSelectRow: Long, inComponent: Long) {
        items.getOrNull(didSelectRow.toInt())?.let { onItemPicked(it) }
    }
}
