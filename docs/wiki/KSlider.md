# 📘 `KSlider`

A cross-platform slider.

---

## 🔹 Overview

| Element | Type | Description |
|---|---|---|
| `KSlider` | Composable | Slider for a value within a range. |
| `KSliderColors` | Data class | Colors of a `KSlider`. |

---

## 🧩 KSlider

**Definition**: A slider that renders the native control on each platform.

### Parameters

| Parameter | Type | Default | Description |
|---|---|---|---|
| `value` | `Float` | — | The current value, within `valueRange`. |
| `onValueChange` | `(Float) -> Unit` | — | Called while the user drags. |
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the slider (fills the width by default). |
| `enabled` | `Boolean` | `true` | Whether the slider can be dragged. |
| `contentDescription` | `String?` | `null` | Screen-reader label: what the slider controls. |
| `valueRange` | `ClosedFloatingPointRange<Float>` | `0f..1f` | Selectable range. |
| `steps` | `Int` | `0` | Discrete values between the ends (`0` = continuous). |
| `onValueChangeFinished` | `(() -> Unit)?` | `null` | Called when the user releases the slider. |
| `colors` | `KSliderColors` | `KSliderDefaults.colors()` | Slider colors. |

---

## 🎨 KSliderColors

Colors of a `KSlider`.

### Properties

| Property | Type | Description |
|---|---|---|
| `activeTrackColor` | `Color` | Track left of the thumb. |
| `inactiveTrackColor` | `Color` | Track right of the thumb. |
| `thumbColor` | `Color` | Thumb color. |
| `disabledActiveTrackColor` | `Color` | Active track when disabled (Android). |
| `disabledInactiveTrackColor` | `Color` | Inactive track when disabled (Android). |
| `disabledThumbColor` | `Color` | Thumb when disabled (Android). |

---

## 📱 Platform behavior

| Platform | Renders as |
|---|---|
| Android | Material 3 `Slider`. |
| iOS | Native `UISlider` (KrossUI snaps to `steps`, which UISlider does not support). |

---

## ♿ Accessibility

Give `contentDescription` (e.g. "Volume"); users adjust it with swipe up/down.

---

## ✅ Usage Example

```kotlin
var volume by rememberSaveable { mutableStateOf(0.5f) }

KSlider(
    value = volume,
    onValueChange = { volume = it },
    contentDescription = "Volume"
)
```

**Steps**

```kotlin
KSlider(
    value = rating,
    onValueChange = { rating = it },
    valueRange = 0f..100f,
    steps = 4, // 0, 20, 40, 60, 80, 100
    onValueChangeFinished = { save(rating) }
)
```

---

← [Back to Home](Home)
