package com.farimarwat.krossui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

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
