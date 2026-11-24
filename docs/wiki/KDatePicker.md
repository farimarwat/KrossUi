# 📘 `KDatePicker`

A cross-platform date picker dialog using `kotlinx.datetime.LocalDate`.

---

## 🔹 Overview

| Element | Type | Description |
|---|---|---|
| `KDatePicker` | Composable | Native date picker in a dialog. |
| `KDatePickerColors` | Data class | Colors of a `KDatePicker`. |

---

## 🧩 KDatePicker

**Definition**: A native date picker shown in a dialog. Dates are calendar dates (`LocalDate`), so the picked day is the same on every device and time zone.

### Parameters

| Parameter | Type | Default | Description |
|---|---|---|---|
| `show` | `Boolean` | — | Whether the picker is visible. |
| `onDateSelected` | `(LocalDate) -> Unit` | — | Called with the chosen date when the user confirms. |
| `onDismiss` | `() -> Unit` | `{}` | Called when dismissed without choosing. |
| `initialDate` | `LocalDate?` | `null` | Date selected when the picker opens (`null` = today). |
| `colors` | `KDatePickerColors` | `KDatePickerDefaults.colors()` | Picker colors. |

---

## 🎨 KDatePickerColors

Colors of a `KDatePicker`.

### Properties

| Property | Type | Description |
|---|---|---|
| `containerColor` | `Color` | Dialog / picker background. |
| `contentColor` | `Color` | Day text color (Android). |
| `footerContainerColor` | `Color` | Footer background (iOS; Android buttons sit in the dialog). |
| `footerContentColor` | `Color` | Cancel / Done button color. |

---

## 📱 Platform behavior

| Platform | Renders as |
|---|---|
| Android | Material 3 `DatePicker` in Material's `DatePickerDialog`. |
| iOS | Native wheel `UIDatePicker` with Cancel / Done. |

> Requires `kotlinx-datetime` (included by KrossUI as an `api` dependency). Planned: `KTimePicker`, `KDateTimePicker` (GitHub issue #1).

---

## ♿ Accessibility

Native pickers are fully accessible; nothing extra needed.

---

## ✅ Usage Example

```kotlin
var show by remember { mutableStateOf(false) }
var date by rememberSaveable { mutableStateOf<LocalDate?>(null) }

KButton(text = "Pick a date", onClick = { show = true })

KDatePicker(
    show = show,
    onDateSelected = { date = it; show = false },
    onDismiss = { show = false },
    initialDate = date
)
```

---

← [Back to Home](Home)
