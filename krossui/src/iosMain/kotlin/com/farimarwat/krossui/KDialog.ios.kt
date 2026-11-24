package com.farimarwat.krossui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberUpdatedState
import platform.UIKit.UIAlertAction
import platform.UIKit.UIAlertActionStyleCancel
import platform.UIKit.UIAlertActionStyleDefault
import platform.UIKit.UIAlertController
import platform.UIKit.UIAlertControllerStyleAlert
import platform.UIKit.UIApplication
import platform.UIKit.UIViewController
import platform.UIKit.UIWindow
import platform.UIKit.UIWindowScene

@Composable
public actual fun KDialog(
    show: Boolean,
    title: String,
    message: String,
    onConfirm: () -> Unit,
    confirmText: String,
    cancelText: String?,
    onCancel: () -> Unit,
    onDismiss: () -> Unit
) {
    if (!show) return

    // The alert outlives individual recompositions, so always call the latest callbacks
    val currentOnConfirm by rememberUpdatedState(onConfirm)
    val currentOnCancel by rememberUpdatedState(onCancel)
    val currentOnDismiss by rememberUpdatedState(onDismiss)

    DisposableEffect(title, message, confirmText, cancelText) {
        // Set once the user taps an action; UIKit then dismisses the alert itself
        var handled = false

        val alert = UIAlertController.alertControllerWithTitle(
            title = title,
            message = message,
            preferredStyle = UIAlertControllerStyleAlert
        )

        alert.addAction(
            UIAlertAction.actionWithTitle(confirmText, UIAlertActionStyleDefault) { _ ->
                handled = true
                currentOnConfirm()
                currentOnDismiss()
            }
        )

        if (cancelText != null) {
            alert.addAction(
                UIAlertAction.actionWithTitle(cancelText, UIAlertActionStyleCancel) { _ ->
                    handled = true
                    currentOnCancel()
                    currentOnDismiss()
                }
            )
        }

        topViewController()?.presentViewController(alert, animated = true, completion = null)

        onDispose {
            // show became false (or the dialog left composition) while the alert is still visible
            if (!handled && alert.presentingViewController != null) {
                alert.dismissViewControllerAnimated(true, completion = null)
            }
        }
    }
}

/** Finds the view controller currently on top, so the alert can be presented over modals too. */
private fun topViewController(): UIViewController? {
    val keyWindow = UIApplication.sharedApplication.connectedScenes
        .filterIsInstance<UIWindowScene>()
        .flatMap { scene -> scene.windows.filterIsInstance<UIWindow>() }
        .firstOrNull { it.isKeyWindow() }

    var top = keyWindow?.rootViewController
    while (top?.presentedViewController != null) {
        top = top.presentedViewController
    }
    return top
}
