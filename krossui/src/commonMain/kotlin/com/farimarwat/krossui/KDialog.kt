package com.farimarwat.krossui

import androidx.compose.runtime.Composable

/**
 * A native alert dialog (Material 3 AlertDialog on Android, UIAlertController on iOS).
 *
 * @param show Whether the dialog is visible.
 * @param title The dialog title.
 * @param message The dialog message.
 * @param onConfirm Called when the confirm button is tapped.
 * @param confirmText The confirm button label.
 * @param cancelText The cancel button label; `null` hides the cancel button.
 * @param onCancel Called when the cancel button is tapped.
 * @param onDismiss Called when the dialog closes (on iOS also after confirm/cancel).
 */
@Composable
public expect fun KDialog(
    show: Boolean,
    title: String,
    message: String,
    onConfirm: () -> Unit,
    confirmText: String = "OK",
    cancelText: String? = null,
    onCancel: () -> Unit = {},
    onDismiss: () -> Unit = {}
)
