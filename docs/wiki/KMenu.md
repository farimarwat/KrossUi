# 📘 `KMenu`

A cross-platform button that opens a native menu.

---

## 🔹 Overview

| Element | Type | Description |
|---|---|---|
| `KMenu` | Composable | Popup menu with icon support. |
| `KMenuColors` | Data class | Colors of a `KMenu`. |
| `KMenuItem` | Data class | One menu entry. |

---

## 🧩 KMenu

**Definition**: A button that opens a native menu of items. The button shows the icon when one is given for the platform, otherwise `title`.

### Parameters

| Parameter | Type | Default | Description |
|---|---|---|---|
| `items` | `List<KMenuItem>` | — | Menu entries. |
| `onItemClick` | `(KMenuItem) -> Unit` | — | Called with the entry the user picked. |
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the menu button. |
| `title` | `String` | `""` | Text of the button when no icon is given. |
| `iosIcon` | `String?` | `null` | SF Symbol for the button on iOS, e.g. `"ellipsis.circle"`. |
| `androidIcon` | `DrawableResource?` | `null` | Drawable for the button on Android. |
| `contentDescription` | `String?` | `null` | Screen-reader label (defaults to `title`); set it for icon-only menus. |
| `colors` | `KMenuColors` | `KMenuDefaults.colors()` | Menu colors. |

---

## 📄 KMenuItem

| Property | Type | Default | Description |
|---|---|---|---|
| `title` | `String` | — | Entry text. |
| `androidIcon` | `DrawableResource?` | `null` | Icon on Android. |
| `iosIcon` | `String?` | `null` | SF Symbol on iOS. |
| `enabled` | `Boolean` | `true` | Disabled entries are shown but cannot be picked. |

---

## 🎨 KMenuColors

Colors of a `KMenu`.

### Properties

| Property | Type | Description |
|---|---|---|
| `containerColor` | `Color` | Menu background (Android). |
| `contentColor` | `Color` | Item / button text color. |
| `disabledContentColor` | `Color` | Disabled item color (Android). |

---

## 📱 Platform behavior

| Platform | Renders as |
|---|---|
| Android | `IconButton` or `TextButton` that opens a Material 3 `DropdownMenu`. |
| iOS | System `UIButton` that opens a native `UIMenu`. |

---

## ♿ Accessibility

Give `contentDescription` for icon-only menus (e.g. "More options").

---

## ✅ Usage Example

```kotlin
KMenu(
    items = listOf(KMenuItem("Edit", iosIcon = "pencil"), KMenuItem("Delete", enabled = false)),
    onItemClick = { item -> /* handle */ },
    title = "Actions"
)
```

---

← [Back to Home](Home)
