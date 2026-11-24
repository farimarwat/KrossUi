# Migrating from KrossUI 1.x to 2.0

KrossUI 2.0 cleans up the public API. **Your 1.x code keeps compiling**: the old names still exist,
marked `@Deprecated`, and your IDE offers a quick-fix (Alt+Enter → *Replace with …*) for each one.
The deprecated API will be removed in 3.0.

## Requirements

- Kotlin **2.4** or newer
- Compose Multiplatform **1.12** or newer
- iOS targets: `iosArm64`, `iosSimulatorArm64` (Intel simulators, `iosX64`, are no longer supported)

## 1. One package

Everything is now in `com.farimarwat.krossui`.

```kotlin
// 1.x
import com.farimarwat.krossui.components.KButton.KButton
import com.farimarwat.krossui.components.KButton.KButtonDefaults
import com.farimarwat.krossui.components.ColorUtils.PlatformColors

// 2.0
import com.farimarwat.krossui.KButton
import com.farimarwat.krossui.KButtonDefaults
import com.farimarwat.krossui.PlatformColors
```

## 2. Renames

| 1.x | 2.0 |
|---|---|
| `KOutLinedButton` | `KOutlinedButton` |
| `isEnabled` | `enabled` |
| `isReadOnly` (`KTextField`) | `readOnly` |
| `onValueChanged` (`KSlider`) | `onValueChange` |
| `iOSIcon` (`KMenu`) | `iosIcon` |
| `padding: KPadding` (`KTextField`) | `contentPadding: PaddingValues` |
| `KButtonDefaults.DefaultFontSize`, `KOutLinedButtonDefaults.DefaultFontSize` | `…Defaults.FontSize` |
| `KProgressIndicatorDefaults.DefaultSize` | `KProgressIndicatorDefaults.Size` |
| `KTextFieldDefaults.Padding` | `KTextField(contentPadding = …)`; `null` (default) = platform padding |

## 3. Sizes are `Dp`

`cornerRadius`, `borderWidth` and `KProgressIndicator(size)` take `Dp` instead of `Double`/`Int`.

```kotlin
// 1.x
KButton(text = "Save", onClick = {}, cornerRadius = 12.0)
// 2.0
KButton(text = "Save", onClick = {}, cornerRadius = 12.dp)
```

## 4. Parameter order

Required parameters come first, then `modifier`, then optional ones — the same order as Material.
Calls that use named arguments are unaffected. Positional calls need updating, for example:

```kotlin
// 1.x
KTabBar(Modifier.fillMaxWidth(), tabs, selected, onTabClick = { selected = it })
// 2.0
KTabBar(tabs, selected, onTabClick = { selected = it }, modifier = Modifier.fillMaxWidth())
```

## 5. Native look by default

All defaults are now **unspecified**, which means "use the platform's native look":

- **Android** components take their colors, shapes and text sizes from your app's `MaterialTheme`
  (including dark mode and Material You). Without a theme you get Material's baseline colors.
- **iOS** components keep UIKit's system appearance: dark mode, the app tint color, Dynamic Type sizes,
  and Liquid Glass on iOS 26.
- `KTextField` on Android is now Material 3's outlined text field.

Pass a value only where you want to override the native look, e.g.
`KButtonDefaults.colors(containerColor = Color.Red)` or `cornerRadius = 12.dp`.
The deprecated 1.x `…Defaults` objects keep their old fixed values, so 1.x code looks the same until migrated.

## 6. Other changes

- `KSlider`: `value` and `onValueChange` are now required.
- `KProgressIndicator` now accepts a `modifier`.
- `KMenu`: the default `title` is now empty instead of `"Hello"`.
- `KMenu` on Android applies `modifier` to the menu button (it was applied to the dropdown).
- `KIconButtonDefaults.colors()` now accepts color parameters like the other components.
- All `*Colors` classes are `@Immutable`.
- `KSwitch` on Android no longer stretches to full width; size it with `modifier` if needed.
- `KDatePicker` on Android now opens at `initialDate` (it was ignored before).
- `KMenu` on Android shows a text button (not a filled button) when no icon is given, as Material menus do.

## What the compatibility layer does not cover

Code that touches renamed **color fields** directly must be updated by hand:

| Class | 1.x field / parameter | 2.0 |
|---|---|---|
| `KOutlinedButtonColors` | `backgroundColor`, `textColor`, `disabledBackgroundColor`, `disabledTextColor` | `containerColor`, `contentColor`, `disabledContainerColor`, `disabledContentColor` |
| `KMenuColors` | `backgroundColor` | `containerColor` |
| `KTabBarColors` | `backgroundColor` | `containerColor` |

Creating colors through `…Defaults.colors(...)` keeps working, including the old parameter names
when you use the old (deprecated) `Defaults` objects.
