# 📘 `KSecureField`

A cross-platform password field.

---

## 🔹 Overview

| Element | Type | Description |
|---|---|---|
| `KSecureField` | Composable | Single-line field whose text is hidden. |

---

## 🧩 KSecureField

**Definition**: A password field: a single-line native text field whose text is hidden. Same styling as [KTextField](KTextField).

### Parameters

| Parameter | Type | Default | Description |
|---|---|---|---|
| `value` | `String` | — | The current text. |
| `onValueChange` | `(String) -> Unit` | — | Called with the new text. |
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the field. |
| `enabled` | `Boolean` | `true` | Whether the field can be edited. |
| `contentDescription` | `String?` | `null` | Screen-reader label (defaults to the placeholder). |
| `placeholder` | `String` | `""` | Text shown when empty. |
| `fontSize` | `TextUnit` | `TextUnit.Unspecified` | Text size (unspecified = native). |
| `imeAction` | `ImeAction` | `ImeAction.Done` | Action key on the keyboard. |
| `onImeAction` | `() -> Unit` | `{}` | Called when the action key is pressed. |
| `contentPadding` | `PaddingValues?` | `null` | Space between border and text (`null` = native). |
| `borderWidth` | `Dp` | `KTextFieldDefaults.BorderWidth` | Border width. |
| `cornerRadius` | `Dp` | `KTextFieldDefaults.CornerRadius` | Corner radius. |
| `colors` | `KTextFieldColors` | `KTextFieldDefaults.colors()` | Field colors — see [KTextField](KTextField). |

---

## 📱 Platform behavior

| Platform | Renders as |
|---|---|
| Android | Material 3 outlined text field with password masking. |
| iOS | Secure `UITextField` (`isSecureTextEntry`). |

---

## ♿ Accessibility

Screen readers announce it as a password field and do not read the characters.

---

## ✅ Usage Example

```kotlin
var password by rememberSaveable { mutableStateOf("") }

KSecureField(
    value = password,
    onValueChange = { password = it },
    placeholder = "Password",
    onImeAction = { /* sign in */ }
)
```

---

← [Back to Home](Home)
