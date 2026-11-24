# 📘 `KIconButton`

A cross-platform button with an icon and/or a title.

---

## 🔹 Overview

| Element | Type | Description |
|---|---|---|
| `KIconButton` | Composable | Button with an icon (Drawable on Android, SF Symbol on iOS). |
| `KIconButtonColors` | Data class | Colors of a `KIconButton` in enabled and disabled states. |
| `KIconButtonDefaults` | Object | Default values: every value is *unspecified* = native look. |

---

## 🧩 KIconButton

**Definition**: A button with an icon and/or a title that renders the native control on each platform.

### Parameters

| Parameter | Type | Default | Description |
|---|---|---|---|
| `onClick` | `() -> Unit` | — | Called when the button is tapped. |
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the button. |
| `enabled` | `Boolean` | `true` | Whether the button can be tapped. |
| `title` | `String?` | `null` | Optional text next to the icon. |
| `iosIcon` | `String?` | `null` | SF Symbol name used on iOS, e.g. `"star.fill"`. |
| `androidIcon` | `DrawableResource?` | `null` | Drawable resource used on Android. |
| `contentDescription` | `String?` | `null` | Screen-reader label; **required when there is no `title`**. |
| `colors` | `KIconButtonColors` | `KIconButtonDefaults.colors()` | Enabled/disabled colors. |
| `fontSize` | `TextUnit` | `KIconButtonDefaults.FontSize` | Title size (unspecified = native). |
| `cornerRadius` | `Dp` | `KIconButtonDefaults.CornerRadius` | Corner radius (Android and iOS before 26). |

---

## 🎨 KIconButtonColors

Colors of a `KIconButton` in enabled and disabled states.

### Properties

| Property | Type | Description |
|---|---|---|
| `containerColor` | `Color` | Background when enabled (iOS 26: tints the glass). |
| `contentColor` | `Color` | Label/icon color when enabled. |
| `disabledContainerColor` | `Color` | Background when disabled (unspecified: the system dims it). |
| `disabledContentColor` | `Color` | Label/icon color when disabled. |

---

## ⚙️ KIconButtonDefaults

Every default is **unspecified**: the platform draws its native look (your app's `MaterialTheme` on Android, the system appearance on iOS). Pass a value only to override it.

### 🎛️ colors(...)

Creates a `KIconButtonColors`. Every parameter defaults to `Color.Unspecified` (platform default).

| Parameter | Type | Default |
|---|---|---|
| `containerColor` | `Color` | `Color.Unspecified` |
| `contentColor` | `Color` | `Color.Unspecified` |
| `disabledContainerColor` | `Color` | `Color.Unspecified` |
| `disabledContentColor` | `Color` | `Color.Unspecified` |

### 🔤 FontSize

| Type | Value | Description |
|---|---|---|
| `TextUnit` | `TextUnit.Unspecified` | Native title size. |

### 🟦 CornerRadius

| Type | Value | Description |
|---|---|---|
| `Dp` | `Dp.Unspecified` | Native shape. |

---

## 📱 Platform behavior

| Platform | Renders as |
|---|---|
| Android | Material 3 `Button` with a leading icon. |
| iOS 26+ | Native **prominent Liquid Glass** button (capsule; system controls shape and disabled look). |
| iOS 15–25 | Native filled `UIButton.Configuration`. |
| iOS 14 | Classic `UIButton` with a background color. |

---

## ♿ Accessibility

Give `contentDescription` when only an icon is shown — otherwise iOS VoiceOver would read the SF Symbol name.

---

## ✅ Usage Example

```kotlin
KIconButton(
    onClick = { /* favourite */ },
    title = "Favourite",
    iosIcon = "star.fill",
    androidIcon = Res.drawable.ic_star
)
```

**Icon only**

```kotlin
KIconButton(
    onClick = { /* favourite */ },
    iosIcon = "star.fill",
    androidIcon = Res.drawable.ic_star,
    contentDescription = "Favourite"
)
```

---

← [Back to Home](Home)
