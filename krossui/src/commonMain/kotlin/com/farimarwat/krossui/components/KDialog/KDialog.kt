package com.farimarwat.krossui.components.KDialog

import androidx.compose.runtime.Composable

/**
 * A platform-specific composable alert dialog that shows a modal with a title,
 * message, and configurable confirm/cancel buttons.
 *
 * @param show Whether the dialog should be visible.
 * @param title The title displayed at the top of the dialog.
 * @param message The main message body of the dialog.
 * @param confirmText The text shown on the confirm action button. Defaults to "OK".
 * @param cancelText Optional text for the cancel button. If null, the cancel button is hidden.
 * @param onConfirm Callback invoked when the confirm button is clicked.
 * @param onCancel Callback invoked when the cancel button is clicked. Defaults to an empty lambda.
 * @param onDismiss Callback invoked when the dialog is dismissed (e.g., outside click or back press). Defaults to an empty lambda.
 */
@Composable
expect fun KDialog(
    show: Boolean,
    title: String,
    message: String,
    confirmText: String = "OK",
    cancelText: String? = null,
    onConfirm: () -> Unit,
    onCancel: () -> Unit = {},
    onDismiss: () -> Unit = {}
)
