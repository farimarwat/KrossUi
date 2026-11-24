package com.farimarwat.krossui

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitView
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.farimarwat.krossui.utils.NativeInteropProperties
import com.farimarwat.krossui.utils.isIosAtLeast
import com.farimarwat.krossui.utils.resolvedForCompose
import com.farimarwat.krossui.utils.toUiColorOrNull
import kotlin.time.Instant
import kotlinx.cinterop.ObjCAction
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime
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
import platform.UIKit.secondarySystemBackgroundColor
import platform.UIKit.systemBackgroundColor
import platform.UIKit.systemBlueColor
import platform.darwin.NSObject

@Composable
public actual fun KDatePicker(
    show: Boolean,
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit,
    initialDate: LocalDate?,
    colors: KDatePickerColors
) {
    if (!show) return

    val startDate = remember(initialDate) { initialDate ?: today() }
    // Date currently on the wheel; reset when a new initialDate is passed
    var currentDate by remember(startDate) { mutableStateOf(startDate) }

    // UIControl holds targets weakly; remember keeps it alive
    val target = remember {
        object : NSObject() {
            var onChanged: (LocalDate) -> Unit = {}

            @ObjCAction
            fun dateChanged(sender: UIDatePicker) {
                onChanged(sender.date.toLocalDate())
            }
        }
    }
    target.onChanged = { currentDate = it }

    // Compose-drawn parts follow the iOS system colors (light/dark) unless a color is given
    val containerColor = colors.containerColor.takeOrElse { UIColor.systemBackgroundColor.resolvedForCompose() }
    val footerContainerColor =
        colors.footerContainerColor.takeOrElse { UIColor.secondarySystemBackgroundColor.resolvedForCompose() }
    val footerContentColor = colors.footerContentColor.takeOrElse { UIColor.systemBlueColor.resolvedForCompose() }

    Popup(
        onDismissRequest = onDismiss,
        alignment = Alignment.Center,
        properties = PopupProperties(clippingEnabled = true)
    ) {
        Column(modifier = Modifier.clip(RoundedCornerShape(16.dp))) {
            UIKitView(
                properties = NativeInteropProperties,
                factory = {
                    UIDatePicker().apply {
                        datePickerMode = UIDatePickerMode.UIDatePickerModeDate
                        if (isIosAtLeast(13, 4)) preferredDatePickerStyle = UIDatePickerStyle.UIDatePickerStyleWheels
                        locale = NSLocale.currentLocale
                        setDate(startDate.toNSDate(), animated = false)
                        addTarget(target, NSSelectorFromString("dateChanged:"), UIControlEventValueChanged)
                    }
                },
                update = { picker ->
                    picker.backgroundColor = colors.containerColor.toUiColorOrNull() ?: UIColor.systemBackgroundColor
                    if (picker.date.toLocalDate() !=
                        currentDate
                    ) {
                        picker.setDate(currentDate.toNSDate(), animated = true)
                    }
                },
                modifier = Modifier
                    .height(260.dp)
                    .fillMaxWidth()
                    .background(containerColor)
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(footerContainerColor),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onDismiss) {
                    Text(text = "Cancel", color = footerContentColor)
                }
                TextButton(onClick = { onDateSelected(currentDate) }) {
                    Text(text = "Done", color = footerContentColor)
                }
            }
        }
    }
}

/** UIDatePicker works in the device time zone: a calendar date maps to its local midnight. */
internal fun LocalDate.toNSDate(): NSDate =
    NSDate.dateWithTimeIntervalSince1970(atStartOfDayIn(TimeZone.currentSystemDefault()).toEpochMilliseconds() / 1000.0)

internal fun NSDate.toLocalDate(): LocalDate = Instant.fromEpochMilliseconds((timeIntervalSince1970 * 1000).toLong())
    .toLocalDateTime(TimeZone.currentSystemDefault())
    .date
