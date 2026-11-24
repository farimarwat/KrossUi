# 📘 `KOutlinedButton`

A cross-platform secondary (outlined) button that renders the native control on each platform.

---

## 🔹 Overview

| Element | Type | Description |
|---|---|---|
| `KOutlinedButton` | Composable | Secondary action button. |
| `KOutlinedButtonColors` | Data class | Colors of a `KOutlinedButton` in enabled and disabled states. |
| `KOutlinedButtonDefaults` | Object | Default values: every value is *unspecified* = native look. |

---

## 🧩 KOutlinedButton

**Definition**: An outlined (secondary) button that renders the native control on each platform.

### Parameters

| Parameter | Type | Default | Description |
|---|---|---|---|
| `text` | `String` | — | Label shown inside the button. |
| `onClick` | `() -> Unit` | — | Called when the button is tapped. |
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the button. |
| `enabled` | `Boolean` | `true` | Whether the button can be tapped. |
| `colors` | `KOutlinedButtonColors` | `KOutlinedButtonDefaults.colors()` | Enabled/disabled colors. |
| `fontSize` | `TextUnit` | `KOutlinedButtonDefaults.FontSize` | Label size (unspecified = native). |
| `cornerRadius` | `Dp` | `KOutlinedButtonDefaults.CornerRadius` | Corner radius (Android and iOS before 26). |
| `borderWidth` | `Dp` | `KOutlinedButtonDefaults.BorderWidth` | Border width (Android and iOS before 26). |

---

## 🎨 KOutlinedButtonColors

Colors of a `KOutlinedButton` in enabled and disabled states.

### Properties

| Property | Type | Description |
|---|---|---|
| `containerColor` | `Color` | Background when enabled (iOS 26: tints the glass). |
| `contentColor` | `Color` | Label/icon color when enabled. |
| `disabledContainerColor` | `Color` | Background when disabled (unspecified: the system dims it). |
| `disabledContentColor` | `Color` | Label/icon color when disabled. |
| `borderColor` | `Color` | Border color when enabled. |
| `disabledBorderColor` | `Color` | Border color when disabled. |

---

## ⚙️ KOutlinedButtonDefaults

Every default is **unspecified**: the platform draws its native look (your app's `MaterialTheme` on Android, the system appearance on iOS). Pass a value only to override it.

### 🎛️ colors(...)

Creates a `KOutlinedButtonColors`. Every parameter defaults to `Color.Unspecified` (platform default).

| Parameter | Type | Default |
|---|---|---|
| `containerColor` | `Color` | `Color.Unspecified` |
| `contentColor` | `Color` | `Color.Unspecified` |
| `disabledContainerColor` | `Color` | `Color.Unspecified` |
| `disabledContentColor` | `Color` | `Color.Unspecified` |
| `borderColor` | `Color` | `Color.Unspecified` |
| `disabledBorderColor` | `Color` | `Color.Unspecified` |

### 🔤 FontSize

| Type | Value | Description |
|---|---|---|
| `TextUnit` | `TextUnit.Unspecified` | Native label size. |

### 🟦 CornerRadius

| Type | Value | Description |
|---|---|---|
| `Dp` | `Dp.Unspecified` | Native shape. |

### ➖ BorderWidth

| Type | Value | Description |
|---|---|---|
| `Dp` | `Dp.Unspecified` | Native border. |

---

## 📱 Platform behavior

| Platform | Renders as |
|---|---|
| Android | Material 3 `OutlinedButton`. |
| iOS 26+ | Native **regular Liquid Glass** button (no border in the iOS 26 design). |
| iOS 15–25 | Native bordered `UIButton.Configuration` (border only when `borderWidth`/`borderColor` is set). |
| iOS 14 | Classic `UIButton` with a border. |

---

## ♿ Accessibility

Read from its `text`; no extra label needed.

---

## ✅ Usage Example

```kotlin
KOutlinedButton(
    text = "Cancel",
    onClick = { /* cancel */ }
)
```

---

← [Back to Home](Home)
