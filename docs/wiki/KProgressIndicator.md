# 📘 `KProgressIndicator`

A cross-platform indeterminate (spinning) progress indicator.

---

## 🔹 Overview

| Element | Type | Description |
|---|---|---|
| `KProgressIndicator` | Composable | Loading spinner. |
| `KProgressIndicatorColors` | Data class | Color of a `KProgressIndicator`. |
| `KProgressIndicatorDefaults` | Object | Default values: every value is *unspecified* = native look. |

---

## 🧩 KProgressIndicator

**Definition**: An indeterminate progress indicator that renders the native control on each platform.

### Parameters

| Parameter | Type | Default | Description |
|---|---|---|---|
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the indicator. |
| `contentDescription` | `String?` | `null` | Screen-reader label, e.g. "Loading". |
| `size` | `Dp` | `KProgressIndicatorDefaults.Size` | Diameter (unspecified = native size). |
| `color` | `Color` | `KProgressIndicatorDefaults.colors().color` | Spinner color (unspecified = native). |

---

## 🎨 KProgressIndicatorColors

Color of a `KProgressIndicator`.

### Properties

| Property | Type | Description |
|---|---|---|
| `color` | `Color` | Spinner color. |

---

## ⚙️ KProgressIndicatorDefaults

Every default is **unspecified**: the platform draws its native look (your app's `MaterialTheme` on Android, the system appearance on iOS). Pass a value only to override it.

### 🎛️ colors(...)

Creates a `KProgressIndicatorColors`. Every parameter defaults to `Color.Unspecified` (platform default).

| Parameter | Type | Default |
|---|---|---|
| `color` | `Color` | `Color.Unspecified` |

### 📏 Size

| Type | Value | Description |
|---|---|---|
| `Dp` | `Dp.Unspecified` | Native size. |

---

## 📱 Platform behavior

| Platform | Renders as |
|---|---|
| Android | Material 3 `CircularProgressIndicator`. |
| iOS | Native `UIActivityIndicatorView` (scaled when `size` is set). |

---

## ♿ Accessibility

iOS reads "In progress" by default; give `contentDescription` to say what is loading.

---

## ✅ Usage Example

```kotlin
KProgressIndicator(contentDescription = "Loading")
```

---

← [Back to Home](Home)
