# 📘 `KButton`

A cross-platform filled button that renders the native control on each platform.

---

## 🔹 Overview

| Element | Type | Description |
|---|---|---|
| `KButton` | Composable | Primary action button. |
| `KButtonColors` | Data class | Colors of a `KButton` in enabled and disabled states. |
| `KButtonDefaults` | Object | Default values: every value is *unspecified* = native look. |

---

## 🧩 KButton

**Definition**: A filled button that renders the native control on each platform.

### Parameters

| Parameter | Type | Default | Description |
|---|---|---|---|
| `text` | `String` | — | Label shown inside the button. |
| `onClick` | `() -> Unit` | — | Called when the button is tapped. |
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the button. |
| `enabled` | `Boolean` | `true` | Whether the button can be tapped. |
| `colors` | `KButtonColors` | `KButtonDefaults.colors()` | Enabled/disabled colors. |
| `fontSize` | `TextUnit` | `KButtonDefaults.FontSize` | Label size (unspecified = native size, scales with system text size). |
| `cornerRadius` | `Dp` | `KButtonDefaults.CornerRadius` | Corner radius (Android and iOS before 26; unspecified = native shape). |

---

## 🎨 KButtonColors

Colors of a `KButton` in enabled and disabled states.

### Properties

| Property | Type | Description |
|---|---|---|
| `containerColor` | `Color` | Background when enabled (iOS 26: tints the glass). |
| `contentColor` | `Color` | Label/icon color when enabled. |
| `disabledContainerColor` | `Color` | Background when disabled (unspecified: the system dims it). |
| `disabledContentColor` | `Color` | Label/icon color when disabled. |

---

## ⚙️ KButtonDefaults

Every default is **unspecified**: the platform draws its native look (your app's `MaterialTheme` on Android, the system appearance on iOS). Pass a value only to override it.

### 🎛️ colors(...)

Creates a `KButtonColors`. Every parameter defaults to `Color.Unspecified` (platform default).

| Parameter | Type | Default |
|---|---|---|
| `containerColor` | `Color` | `Color.Unspecified` |
| `contentColor` | `Color` | `Color.Unspecified` |
| `disabledContainerColor` | `Color` | `Color.Unspecified` |
| `disabledContentColor` | `Color` | `Color.Unspecified` |

### 🔤 FontSize

| Type | Value | Description |
|---|---|---|
| `TextUnit` | `TextUnit.Unspecified` | Native label size. |

### 🟦 CornerRadius

| Type | Value | Description |
|---|---|---|
| `Dp` | `Dp.Unspecified` | Native shape. |

---

## 📱 Platform behavior

| Platform | Renders as |
|---|---|
| Android | Material 3 `Button` (filled). |
| iOS 26+ | Native **prominent Liquid Glass** button (capsule; system controls shape and disabled look). |
| iOS 15–25 | Native filled `UIButton.Configuration`. |
| iOS 14 | Classic `UIButton` with a background color. |

---

## ♿ Accessibility

Read from its `text`; no extra label needed.

---

## ✅ Usage Example

```kotlin
KButton(
    text = "Save",
    onClick = { /* save */ }
)
```

**Custom colors and size**

```kotlin
KButton(
    text = "Delete",
    onClick = { /* delete */ },
    modifier = Modifier.fillMaxWidth(),
    colors = KButtonDefaults.colors(containerColor = Color.Red, contentColor = Color.White),
    cornerRadius = 12.dp
)
```

---

← [Back to Home](Home)
