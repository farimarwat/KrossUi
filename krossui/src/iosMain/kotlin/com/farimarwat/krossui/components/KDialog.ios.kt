package com.farimarwat.krossui.components

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitView
import platform.UIKit.*


@Composable
actual fun KDialog(
    show: Boolean,
    title: String,
    message: String,
    confirmText: String,
    cancelText: String?,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    onDismiss: () -> Unit
) {
    if (show) {
        var shown by remember { mutableStateOf(false) }

        if (!shown) {
            shown = true
            UIKitView(
                factory = {
                    val alert = UIAlertController.alertControllerWithTitle(
                        title = title,
                        message = message,
                        preferredStyle = UIAlertControllerStyleAlert
                    )

                    alert.addAction(
                        UIAlertAction.actionWithTitle(
                            confirmText,
                            UIAlertActionStyleDefault
                        ) { _ ->
                            onConfirm()
                            onDismiss()
                        }
                    )

                    if (cancelText != null) {
                        alert.addAction(
                            UIAlertAction.actionWithTitle(
                                cancelText,
                                UIAlertActionStyleCancel
                            ) { _ ->
                                onCancel()
                                onDismiss()
                            }
                        )
                    }

                    val controller = UIApplication.sharedApplication.keyWindow?.rootViewController
                    controller?.presentViewController(alert, animated = true, completion = null)

                    UIView()
                },
                modifier = Modifier
            )
        }
    }
}
