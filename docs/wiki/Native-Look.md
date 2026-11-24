# 🎨 Native Look

KrossUI shows each platform's own controls and, by default, **does not paint anything itself**.

## Defaults = platform look

Every default (colors, font size, corner radius, border width, size, padding) is *unspecified*:

| Platform | Where the look comes from |
|---|---|
| **Android** | Your app's `MaterialTheme` — brand colors, dark mode, Material You. Without a theme: Material's baseline colors. |
| **iOS 26+** | UIKit's system appearance with **Liquid Glass** (buttons, tab bar, switches, sliders). |
| **iOS before 26** | UIKit's classic appearance (filled / bordered buttons, classic tab bar). |

Apps that opt out of the new iOS design (`UIDesignRequiresCompatibility` in Info.plist) get the classic look on iOS 26 too.

## Customizing

Pass a value only where you want to differ from the native look:

```kotlin
KButton(
    text = "Delete",
    onClick = { },
    colors = KButtonDefaults.colors(containerColor = Color.Red, contentColor = Color.White),
    cornerRadius = 12.dp,
    fontSize = 18.sp
)
```

Notes:
- **iOS 26 glass buttons** have a system-defined capsule shape: `cornerRadius` and `borderWidth` apply on
  Android and older iOS. `containerColor` tints the glass.
- **Font sizes** you set still scale with the user's text size (Dynamic Type / font scale).
- **Disabled colors**: leave them unspecified and each platform dims the control its own way.

## Dark mode

Nothing to do: Android follows your `MaterialTheme` (pass a dark color scheme), iOS follows the system.

## Layout

Native controls size themselves like Compose components: they wrap their natural size, fill when you use
`fillMaxWidth()`, and take fixed sizes from `Modifier.size(...)`. Tappable controls keep a 44 pt (iOS) / 48 dp
(Android) touch target.

← [Back to Home](Home)
