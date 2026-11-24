# 📘 `KDialog`

A cross-platform native alert dialog.

---

## 🔹 Overview

| Element | Type | Description |
|---|---|---|
| `KDialog` | Composable | Alert / confirmation dialog. |

---

## 🧩 KDialog

**Definition**: A native alert dialog with a title, a message, a confirm button and an optional cancel button.

### Parameters

| Parameter | Type | Default | Description |
|---|---|---|---|
| `show` | `Boolean` | — | Whether the dialog is visible. |
| `title` | `String` | — | Dialog title. |
| `message` | `String` | — | Dialog message. |
| `onConfirm` | `() -> Unit` | — | Called when the confirm button is tapped. |
| `confirmText` | `String` | `"OK"` | Confirm button label. |
| `cancelText` | `String?` | `null` | Cancel button label; `null` hides the button. |
| `onCancel` | `() -> Unit` | `{}` | Called when the cancel button is tapped. |
| `onDismiss` | `() -> Unit` | `{}` | Called when the dialog closes (on iOS also after confirm/cancel). |

---

## 📱 Platform behavior

| Platform | Renders as |
|---|---|
| Android | Material 3 `AlertDialog`. |
| iOS | Native `UIAlertController` (presented over the top-most screen). |

---

## ♿ Accessibility

Native dialogs are fully accessible; nothing extra needed.

---

## ✅ Usage Example

```kotlin
var show by remember { mutableStateOf(false) }

KDialog(
    show = show,
    title = "Delete item?",
    message = "This cannot be undone.",
    onConfirm = { delete(); show = false },
    confirmText = "Delete",
    cancelText = "Cancel",
    onCancel = { show = false },
    onDismiss = { show = false }
)
```

---

← [Back to Home](Home)
