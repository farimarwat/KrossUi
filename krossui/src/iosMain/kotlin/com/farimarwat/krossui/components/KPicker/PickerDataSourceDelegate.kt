package com.farimarwat.krossui.components.KPicker

import kotlinx.cinterop.ExperimentalForeignApi
import platform.UIKit.UIPickerView
import platform.UIKit.UIPickerViewDataSourceProtocol
import platform.UIKit.UIPickerViewDelegateProtocol
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
class PickerDataSourceDelegate(
    private val items: List<String>,
    private val onItemPicked: (String) -> Unit
) : NSObject(), UIPickerViewDataSourceProtocol, UIPickerViewDelegateProtocol {

    override fun numberOfComponentsInPickerView(pickerView: UIPickerView): Long {
        return 1L
    }

    override fun pickerView(pickerView: UIPickerView, numberOfRowsInComponent: Long): Long {
        return items.size.toLong()
    }

    override fun pickerView(pickerView: UIPickerView, titleForRow: Long, forComponent: Long): String? {
        val item = items.getOrNull(titleForRow.toInt())
        item?.let{
            onItemPicked(it)
        }
        return item
    }
}

