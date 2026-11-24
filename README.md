# KrossUI 🌐

**KrossUI** is a Kotlin Multiplatform UI component library for **Compose Multiplatform**.
You write one Compose API; each platform shows its **real native control** — **Material 3** on Android and
**UIKit** on iOS, including **Liquid Glass on iOS 26**.

---

## 🚀 Features

- 🧩 **Composable Components** — buttons, text fields, switches, sliders, pickers, date picker, menus, dialogs, tab bar.
- 📱 **Truly native** — Material 3 on Android; UIKit on iOS with Liquid Glass on iOS 26 and the classic look on older iOS.
- 🌗 **Native by default** — dark mode, Dynamic Type / font scale, your `MaterialTheme` and Material You, with no setup.
- 🎨 **Customizable** — colors, sizes, shapes and padding when you want them; everything else stays native.
- ♿ **Accessible** — screen-reader labels, large text, right-to-left layouts and proper touch targets.
- 🛠️ **Developer friendly** — one package, Compose-style API, controlled components, KDoc everywhere.
- 🧪 **Tested** — interaction and screenshot tests on Android, logic tests on iOS, public API checked in CI.

---

## 📦 Installation

commonMain

```kotlin
implementation("io.github.farimarwat:krossui:2.0.0")
```

| Requirement | Version |
|---|---|
| Kotlin | 2.4+ |
| Compose Multiplatform | 1.12+ |
| Android | minSdk 24 |
| iOS | `iosArm64`, `iosSimulatorArm64` |

Upgrading from 1.x? Your code keeps compiling — see the [migration guide](MIGRATION.md).

---

## ⚡ Quick Start

```kotlin
import com.farimarwat.krossui.*

var notifications by rememberSaveable { mutableStateOf(true) }

KSwitch(
    checked = notifications,
    onCheckedChange = { notifications = it },
    contentDescription = "Notifications"
)

KButton(text = "Save", onClick = { /* save */ })
```

---

## 📚 Available Components

| Component | Description |
|---|---|
| `KButton` | Filled (primary) button |
| `KOutlinedButton` | Outlined (secondary) button |
| `KIconButton` | Button with an icon and/or title |
| `KTextField` | Text input |
| `KSecureField` | Password input |
| `KSwitch` | On/off switch |
| `KSlider` | Slider with range and steps |
| `KPicker` | Scrollable wheel picker |
| `KDatePicker` | Native date picker (`LocalDate`) |
| `KMenu` | Popup menu with icon support |
| `KDialog` | Alert / confirmation dialog |
| `KProgressIndicator` | Loading spinner |
| `KTabBar` | Bottom navigation bar |

---

## 📖 Documentation

Refer to the repo's [wiki](https://github.com/farimarwat/KrossUi/wiki): one page per component,
plus guides for getting started, native look, accessibility and performance.

The demo app (`androidApp`, `iosApp`) is a catalog of every component — see
[manual testing](docs/manual-testing.md).

---

## 🙌 Contribute

KrossUI is open to contributions! Bug fixes, docs and new components are welcome — see
[CONTRIBUTING.md](CONTRIBUTING.md).

---

## 📄 License

KrossUI is licensed under the [MIT License](LICENSE).

---

Built with ❤️ by [Farimarwat](https://github.com/farimarwat)
