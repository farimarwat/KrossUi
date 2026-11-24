# 📘 `KTabBar`

A cross-platform bottom navigation bar.

---

## 🔹 Overview

| Element | Type | Description |
|---|---|---|
| `KTabBar` | Composable | Bottom navigation bar. |
| `KTabBarColors` | Data class | Colors of a `KTabBar`. |
| `KTabItem` | Data class | One tab. |

---

## 🧩 KTabBar

**Definition**: A bottom tab bar that renders the native control on each platform.

### Parameters

| Parameter | Type | Default | Description |
|---|---|---|---|
| `tabs` | `List<KTabItem>` | — | Tabs to show. |
| `selectedIndex` | `Int` | — | Index of the selected tab. |
| `onTabClick` | `(Int) -> Unit` | — | Called with the tapped tab's index. |
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the bar (fills the width). |
| `colors` | `KTabBarColors` | `KTabBarDefaults.colors()` | Bar colors. |

---

## 📄 KTabItem

| Property | Type | Description |
|---|---|---|
| `title` | `String` | Tab label. |
| `androidIcon` | `DrawableResource` | Icon on Android. |
| `iosIcon` | `String` | SF Symbol on iOS. |

---

## 🎨 KTabBarColors

Colors of a `KTabBar`.

### Properties

| Property | Type | Description |
|---|---|---|
| `containerColor` | `Color` | Bar background (Android, and iOS before 26). |
| `selectedContentColor` | `Color` | Selected tab icon/label. |
| `unselectedContentColor` | `Color` | Other tabs' icon/label. |

---

## 📱 Platform behavior

| Platform | Renders as |
|---|---|
| Android | Material 3 `NavigationBar`. |
| iOS 26+ | Native floating **Liquid Glass** `UITabBar`. |
| iOS before 26 | Native `UITabBar`. |

> Planned (GitHub issue #1): a clearer bottom-navigation name and a separate top-tabs component.

---

## ♿ Accessibility

Read from each tab's `title`.

---

## ✅ Usage Example

```kotlin
var tab by rememberSaveable { mutableStateOf(0) }

Scaffold(
    bottomBar = {
        KTabBar(
            tabs = listOf(
                KTabItem("Home", Res.drawable.ic_home, "house.fill"),
                KTabItem("Settings", Res.drawable.ic_settings, "gearshape.fill")
            ),
            selectedIndex = tab,
            onTabClick = { tab = it }
        )
    }
) { /* content */ }
```

---

← [Back to Home](Home)
