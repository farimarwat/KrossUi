# 📘 `KPicker`

A cross-platform wheel picker for a list of strings.

---

## 🔹 Overview

| Element | Type | Description |
|---|---|---|
| `KPicker` | Composable | Scrollable wheel/list picker. |
| `KPickerColors` | Data class | Colors of a `KPicker`. |

---

## 🧩 KPicker

**Definition**: A scrolling wheel picker. It is **controlled**: it shows `selectedIndex` and reports the user's choice through `onSelectedIndexChange`.

### Parameters

| Parameter | Type | Default | Description |
|---|---|---|---|
| `items` | `List<String>` | — | Values to choose from. |
| `selectedIndex` | `Int` | — | Index of the selected item. |
| `onSelectedIndexChange` | `(Int) -> Unit` | — | Called with the index the user selected. |
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the picker. |
| `contentDescription` | `String?` | `null` | Screen-reader label: what is being picked. |
| `colors` | `KPickerColors` | `KPickerDefaults.colors()` | Picker colors. |

---

## 🎨 KPickerColors

Colors of a `KPicker`.

### Properties

| Property | Type | Description |
|---|---|---|
| `containerColor` | `Color` | Background. |
| `contentColor` | `Color` | Selected item text color. |
| `disabledContainerColor` | `Color` | Reserved. |
| `disabledContentColor` | `Color` | Text color of the items that are not selected (Android). |

---

## 📱 Platform behavior

| Platform | Renders as |
|---|---|
| Android | Wheel-style list (Material colors); rows are selectable radio items, 48 dp high. |
| iOS | Native `UIPickerView` wheel. |

---

## ♿ Accessibility

Give `contentDescription` (e.g. "Fruit"). Android rows are announced as radio buttons with their selected state.

---

## ✅ Usage Example

```kotlin
val fruits = listOf("Apple", "Mango", "Grapes")
var index by rememberSaveable { mutableStateOf(0) }

KPicker(
    items = fruits,
    selectedIndex = index,
    onSelectedIndexChange = { index = it },
    contentDescription = "Fruit"
)
```

---

← [Back to Home](Home)
