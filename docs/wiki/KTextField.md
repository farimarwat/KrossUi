# 📘 `KTextField`

A cross-platform text input that renders the native control on each platform.

---

## 🔹 Overview

| Element | Type | Description |
|---|---|---|
| `KTextField` | Composable | Single-line text input (multi-line on Android). |
| `KTextFieldColors` | Data class | Colors of a `KTextField` / `KSecureField`. |
| `KTextFieldDefaults` | Object | Default values: every value is *unspecified* = native look. |

---

## 🧩 KTextField

**Definition**: A text input that renders the native control on each platform.

### Parameters

| Parameter | Type | Default | Description |
|---|---|---|---|
| `value` | `String` | — | The current text. |
| `onValueChange` | `(String) -> Unit` | — | Called with the new text when the user edits it. |
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the field. |
| `enabled` | `Boolean` | `true` | Whether the field can be focused and edited. |
| `readOnly` | `Boolean` | `false` | Text can be selected but not edited. |
| `contentDescription` | `String?` | `null` | Screen-reader label (defaults to the placeholder). |
| `placeholder` | `String` | `""` | Text shown when `value` is empty. |
| `fontSize` | `TextUnit` | `TextUnit.Unspecified` | Text size (unspecified = native, scales with system text size). |
| `maxLines` | `Int` | `1` | Maximum visible lines (Android; the iOS `UITextField` is single-line). |
| `keyboardType` | `KeyboardType` | `KeyboardType.Text` | Keyboard shown while editing. For passwords use [KSecureField](KSecureField). |
| `imeAction` | `ImeAction` | `ImeAction.Default` | Action key on the keyboard. |
| `onImeAction` | `() -> Unit` | `{}` | Called when the action key is pressed. |
| `contentPadding` | `PaddingValues?` | `null` | Space between border and text (`null` = native padding). |
| `borderWidth` | `Dp` | `KTextFieldDefaults.BorderWidth` | Border width (unspecified = native). |
| `cornerRadius` | `Dp` | `KTextFieldDefaults.CornerRadius` | Corner radius (unspecified = native). |
| `colors` | `KTextFieldColors` | `KTextFieldDefaults.colors()` | Field colors. |

---

## 🎨 KTextFieldColors

Colors of a `KTextField` / `KSecureField`.

### Properties

| Property | Type | Description |
|---|---|---|
| `textColor` | `Color` | Text color. |
| `unfocusedBackgroundColor` | `Color` | Background when not focused. |
| `focusedBackgroundColor` | `Color` | Background when focused. |
| `placeholderColor` | `Color` | Placeholder color. |
| `unfocusedBorderColor` | `Color` | Border when not focused. |
| `focusedBorderColor` | `Color` | Border when focused. |
| `disabledTextColor` | `Color` | Text color when disabled. |
| `disabledBorderColor` | `Color` | Border when disabled. |
| `readOnlyTextColor` | `Color` | Text color when read-only. |

---

## ⚙️ KTextFieldDefaults

Every default is **unspecified**: the platform draws its native look (your app's `MaterialTheme` on Android, the system appearance on iOS). Pass a value only to override it.

### 🎛️ colors(...)

Creates a `KTextFieldColors`. Every parameter defaults to `Color.Unspecified` (platform default).

| Parameter | Type | Default |
|---|---|---|
| `textColor` | `Color` | `Color.Unspecified` |
| `unfocusedBackgroundColor` | `Color` | `Color.Unspecified` |
| `focusedBackgroundColor` | `Color` | `Color.Unspecified` |
| `placeholderColor` | `Color` | `Color.Unspecified` |
| `unfocusedBorderColor` | `Color` | `Color.Unspecified` |
| `focusedBorderColor` | `Color` | `Color.Unspecified` |
| `disabledTextColor` | `Color` | `Color.Unspecified` |
| `disabledBorderColor` | `Color` | `Color.Unspecified` |
| `readOnlyTextColor` | `Color` | `Color.Unspecified` |

### ➖ BorderWidth

| Type | Value | Description |
|---|---|---|
| `Dp` | `Dp.Unspecified` | Native border. |

### 🟦 CornerRadius

| Type | Value | Description |
|---|---|---|
| `Dp` | `Dp.Unspecified` | Native shape. |

---

## 📱 Platform behavior

| Platform | Renders as |
|---|---|
| Android | Material 3 **outlined text field** (Material's own decoration and container). |
| iOS | Native `UITextField`: the standard rounded field, or a custom border when `borderWidth`, `cornerRadius` or a border color is set. |

> Planned (GitHub issue #1): error state, label, supporting text, leading/trailing icons.

---

## ♿ Accessibility

Read from the placeholder and text; give `contentDescription` for a more specific label.

---

## ✅ Usage Example

```kotlin
var name by rememberSaveable { mutableStateOf("") }

KTextField(
    value = name,
    onValueChange = { name = it },
    modifier = Modifier.fillMaxWidth(),
    placeholder = "Name",
    imeAction = ImeAction.Done,
    onImeAction = { /* submit */ }
)
```

---

← [Back to Home](Home)
