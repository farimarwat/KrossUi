# 📘 `KSwitch`

A cross-platform on/off switch.

---

## 🔹 Overview

| Element | Type | Description |
|---|---|---|
| `KSwitch` | Composable | Toggle switch. |
| `KSwitchColors` | Data class | Colors of a `KSwitch`. |

---

## 🧩 KSwitch

**Definition**: An on/off switch that renders the native control on each platform.

### Parameters

| Parameter | Type | Default | Description |
|---|---|---|---|
| `checked` | `Boolean` | — | Whether the switch is on. |
| `onCheckedChange` | `(Boolean) -> Unit` | — | Called with the new state. |
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the switch. |
| `enabled` | `Boolean` | `true` | Whether the switch can be toggled. |
| `contentDescription` | `String?` | `null` | Screen-reader label: what the switch controls. |
| `colors` | `KSwitchColors` | `KSwitchDefaults.colors()` | Switch colors. |

---

## 🎨 KSwitchColors

Colors of a `KSwitch`.

### Properties

| Property | Type | Description |
|---|---|---|
| `thumbColor` | `Color` | Thumb color. |
| `trackColorChecked` | `Color` | Track color when on. |
| `trackColorUnchecked` | `Color` | Track/outline color when off. |

---

## 📱 Platform behavior

| Platform | Renders as |
|---|---|
| Android | Material 3 `Switch`. |
| iOS | Native `UISwitch` (system green by default; iOS 26 style on iOS 26). |

---

## ♿ Accessibility

Give `contentDescription` (e.g. "Wi-Fi") — announced as "Wi-Fi, switch, on".

---

## ✅ Usage Example

```kotlin
var wifi by rememberSaveable { mutableStateOf(true) }

KSwitch(
    checked = wifi,
    onCheckedChange = { wifi = it },
    contentDescription = "Wi-Fi"
)
```

---

← [Back to Home](Home)
