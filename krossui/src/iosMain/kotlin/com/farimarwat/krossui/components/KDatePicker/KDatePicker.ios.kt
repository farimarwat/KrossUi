package com.farimarwat.krossui.components.KDatePicker

import com.farimarwat.krossui.utils.NativeInteropProperties
import com.farimarwat.krossui.utils.isIosAtLeast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.farimarwat.krossui.utils.toUiColor
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCAction
import platform.Foundation.NSDate
import platform.Foundation.NSLocale
import platform.Foundation.NSSelectorFromString
import platform.Foundation.currentLocale
import platform.Foundation.dateWithTimeIntervalSince1970
import platform.Foundation.timeIntervalSince1970
import platform.UIKit.UIColor
import platform.UIKit.UIControlEventValueChanged
import platform.UIKit.UIDatePicker
import platform.UIKit.UIDatePickerMode
import platform.UIKit.UIDatePickerStyle
import platform.UIKit.UIDevice

import platform.UIKit.systemBackgroundColor

import platform.darwin.NSObject
@OptIn(ExperimentalForeignApi::class, ExperimentalComposeUiApi::class)
@Composable
actual fun KDatePicker(
    show: Boolean,
    initialDate: Long,
    colors: KDatePickerColors,
    onDismiss: () -> Unit,
    onDateSelected: (Long) -> Unit
) {
    if (show) {
        // Date currently chosen in the wheel; reset when a new initialDate is passed
        var currentDate by remember(initialDate) { mutableStateOf(initialDate) }

        // UIControl holds targets weakly, so the target must be remembered to stay alive
        val target = remember {
            object : NSObject() {
                var onChanged: (Long) -> Unit = {}

                @ObjCAction
                fun dateChanged(sender: UIDatePicker) {
                    onChanged((sender.date.timeIntervalSince1970 * 1000).toLong())
                }
            }
        }
        target.onChanged = { currentDate = it }

        val datePicker = remember {
            UIDatePicker().apply {
                datePickerMode = UIDatePickerMode.UIDatePickerModeDate
                if (isIosAtLeast(13, 4)) {
                    preferredDatePickerStyle = UIDatePickerStyle.UIDatePickerStyleWheels
                }

                val nsDate = NSDate.dateWithTimeIntervalSince1970(initialDate / 1000.0)
                setDate(nsDate, animated = true)

                locale = NSLocale.currentLocale
                backgroundColor = colors.containerColor.toUiColor()
                addTarget(
                    target = target,
                    action = NSSelectorFromString("dateChanged:"),
                    forControlEvents = UIControlEventValueChanged
                )
            }
        }
        Popup(
            onDismissRequest = onDismiss,
            alignment = Alignment.Center,
            properties = PopupProperties(
                clippingEnabled = true
            )
        ){
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
            ){
                UIKitView(
                    properties = NativeInteropProperties,
                    factory = {
                        datePicker.sizeToFit()
                        datePicker
                    },
                    modifier = Modifier
                        .height(260.dp)
                        .fillMaxWidth()
                        .background(colors.containerColor),
                    update = { picker ->
                        picker.backgroundColor = colors.containerColor.toUiColor()
                        // Sync the wheel only when the state differs (e.g. a new initialDate)
                        val pickerDate = (picker.date.timeIntervalSince1970 * 1000).toLong()
                        if (kotlin.math.abs(pickerDate - currentDate) >= 1000) {
                            picker.setDate(
                                NSDate.dateWithTimeIntervalSince1970(currentDate / 1000.0),
                                animated = true
                            )
                        }
                    }
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(colors.footerContainerColor)
                    ,
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = {
                        onDismiss()
                    }) {
                        Text(
                            text = "Cancel",
                            color = colors.footerContentColor
                        )
                    }
                    TextButton(onClick = {
                        onDateSelected(currentDate)
                    }) {
                        Text(
                            text = "Done",
                            color = colors.footerContentColor
                        )
                    }
                }

            }
        }
    }
}
