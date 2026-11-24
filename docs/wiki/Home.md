# 🧩 Welcome to KrossUI

**KrossUI** is a UI component library for **Kotlin Multiplatform** with **Compose Multiplatform**.
You write one Compose API; each platform shows its **real native control**:
**Material 3** on Android and **UIKit** on iOS — including **Liquid Glass on iOS 26**.

---

## 🚀 Features

- ✨ Ready-to-use components: `KButton`, `KTextField`, `KDialog`, `KMenu`, `KDatePicker`, `KTabBar`, and more
- 📱 **Truly native**: Material 3 on Android, UIKit on iOS — iOS 26 Liquid Glass, classic look on older iOS
- 🌗 **Native by default**: dark mode, Dynamic Type / font scale, your `MaterialTheme` and Material You — with no setup
- 🎨 **Customizable** when you want it: colors, sizes, shapes, padding (everything defaults to the platform look)
- ♿ **Accessible**: screen-reader labels, large text, right-to-left layouts, touch-target sizes
- 🧠 **Platform-aware icons**: Android uses `Drawable`, iOS uses `SF Symbols`
- 🧪 **Tested**: interaction and screenshot tests on Android, logic tests on iOS, public API checked in CI

---

## 📦 Installation

```kotlin
// commonMain
implementation("io.github.farimarwat:krossui:2.0.0")
```

| Requirement | Version |
|---|---|
| Kotlin | 2.4+ |
| Compose Multiplatform | 1.12+ |
| Android | minSdk 24 |
| iOS | `iosArm64`, `iosSimulatorArm64` (iOS 14+) |

See **[Getting Started](Getting-Started)** for a first screen.

---

## 📚 Available Components

| Component | Description |
|---|---|
| [`KButton`](KButton) | Filled (primary) button |
| [`KOutlinedButton`](KOutlinedButton) | Outlined (secondary) button |
| [`KIconButton`](KIconButton) | Button with an icon and/or title |
| [`KTextField`](KTextField) | Text input |
| [`KSecureField`](KSecureField) | Password input |
| [`KSwitch`](KSwitch) | On/off switch |
| [`KSlider`](KSlider) | Slider with range and steps |
| [`KPicker`](KPicker) | Scrollable wheel picker |
| [`KDatePicker`](KDatePicker) | Native date picker (`LocalDate`) |
| [`KMenu`](KMenu) | Popup menu with icon support |
| [`KDialog`](KDialog) | Alert / confirmation dialog |
| [`KProgressIndicator`](KProgressIndicator) | Loading spinner |
| [`KTabBar`](KTabBar) | Bottom navigation bar |

---

## 📖 Guides

- [Getting Started](Getting-Started)
- [Native Look](Native-Look) — how KrossUI looks on each platform and how to customize it
- [Accessibility](Accessibility)
- [Performance](Performance)
- [Migrating from 1.x](Migrating-from-1.x)
