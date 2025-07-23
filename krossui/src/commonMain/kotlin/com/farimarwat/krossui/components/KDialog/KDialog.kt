package com.farimarwat.krossui.components.KDialog

import androidx.compose.runtime.Composable

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