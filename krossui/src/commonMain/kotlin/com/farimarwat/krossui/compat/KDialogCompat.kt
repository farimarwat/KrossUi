@file:Suppress("ktlint:standard:package-name", "DEPRECATION")

package com.farimarwat.krossui.components.KDialog

// KrossUI 1.x API, kept so existing code compiles. Removed in 3.0 — see MIGRATION.md.

import androidx.compose.runtime.Composable

@Deprecated(
    "Moved to com.farimarwat.krossui (KrossUI 2.0). See MIGRATION.md.",
    ReplaceWith(
        "com.farimarwat.krossui.KDialog(show = show, title = title, message = message, onConfirm = onConfirm, confirmText = confirmText, cancelText = cancelText, onCancel = onCancel, onDismiss = onDismiss)",
        "androidx.compose.ui.unit.dp"
    )
)
@Composable
public fun KDialog(
    show: Boolean,
    title: String,
    message: String,
    confirmText: String = "OK",
    cancelText: String? = null,
    onConfirm: () -> Unit,
    onCancel: () -> Unit = {},
    onDismiss: () -> Unit = {}
): Unit = com.farimarwat.krossui.KDialog(
    show = show,
    title = title,
    message = message,
    onConfirm = onConfirm,
    confirmText = confirmText,
    cancelText = cancelText,
    onCancel = onCancel,
    onDismiss = onDismiss
)
