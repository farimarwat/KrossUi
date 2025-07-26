package com.farimarwat.krossui.components.KDatePicker

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
        var currentDate = remember { initialDate }

        val datePicker = remember {
            UIDatePicker().apply {
                val target =  object : NSObject() {
                    @ObjCAction
                    fun dateChanged() {
                        val timeInterval = this@apply.date.timeIntervalSince1970
                        val timestamp = (timeInterval * 1000).toLong()
                        currentDate = timestamp
                    }
                }
                datePickerMode = UIDatePickerMode.UIDatePickerModeDate
                backgroundColor = UIColor.redColor

                if (UIDevice.currentDevice.systemVersion.substringBefore(".").toInt() >= 13.4) {
                    preferredDatePickerStyle = UIDatePickerStyle.UIDatePickerStyleWheels
                }

                val nsDate = NSDate.dateWithTimeIntervalSince1970(initialDate / 1000.0)
                setDate(nsDate, animated = true)

                locale = NSLocale.currentLocale
                backgroundColor = colors.containerColor.toUiColor()
                addTarget(
                    target = target,
                    action = NSSelectorFromString("dateChanged"),
                    forControlEvents = UIControlEventValueChanged
                )
            }
        }
        Popup(
            onDismissRequest = onDismiss,
            alignment = Alignment.Center,
            properties = PopupProperties(
                usePlatformDefaultWidth = true
            )
        ){
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(16.dp))
                    .background(Color.White)
            ){
                UIKitView(
                    factory = {
                        datePicker.sizeToFit()
                        datePicker
                    },
                    modifier = Modifier
                        .height(260.dp)
                        .fillMaxWidth(),
                    update = { picker ->
                        if (currentDate != initialDate) {
                            val nsDate = NSDate.dateWithTimeIntervalSince1970(initialDate / 1000.0)
                            picker.setDate(nsDate, animated = true)
                            currentDate = initialDate
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


