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

    // Track selection manually since we need to find the correct delegate method
    private var lastSelectedRow: Long = -1

    override fun pickerView(pickerView: UIPickerView, titleForRow: Long, forComponent: Long): String? {
        // Check if this is the currently selected row
        val currentSelected = pickerView.selectedRowInComponent(forComponent)
        if (currentSelected != lastSelectedRow && currentSelected == titleForRow) {
            lastSelectedRow = currentSelected
            val selectedItem = items.getOrNull(titleForRow.toInt())
            selectedItem?.let {
                onItemPicked(it)
            }
        }
        return items.getOrNull(titleForRow.toInt())
    }
}

