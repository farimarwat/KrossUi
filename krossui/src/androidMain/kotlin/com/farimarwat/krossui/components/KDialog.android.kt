package com.farimarwat.krossui.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable

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
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                Button(onClick = onConfirm) {
                    Text(confirmText)
                }
            },
            dismissButton = cancelText?.let {
                {
                    Button(onClick = onCancel) {
                        Text(cancelText)
                    }
                }
            },
            title = { Text(title) },
            text = { Text(message) }
        )
    }
}
