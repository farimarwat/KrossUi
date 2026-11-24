# Changelog

## 2.0.0 (unreleased)

Upgrading from 1.x: your code keeps compiling with deprecation warnings — see [MIGRATION.md](MIGRATION.md).

### Native look
- iOS 26: **Liquid Glass** buttons and tab bar; native controls show glass over your Compose content.
- Every default is the platform look: Android follows your `MaterialTheme` (dark mode, Material You);
  iOS follows the system appearance (dark mode, tint, Dynamic Type).
- Android `KTextField` is Material 3's outlined text field; Android `KDatePicker` uses `DatePickerDialog`.

### API
- One package: `com.farimarwat.krossui`.
- Consistent signatures: required parameters, then `modifier`, then `enabled`, then options.
- Sizes are `Dp`; `KTextField` takes `contentPadding: PaddingValues`.
- `KOutLinedButton` → `KOutlinedButton`, `isEnabled` → `enabled`, `iOSIcon` → `iosIcon`.
- `KDatePicker` uses `kotlinx.datetime.LocalDate`.
- `KPicker` is controlled (`selectedIndex` + `onSelectedIndexChange`).
- `KSlider` gains `valueRange`, `steps`, `enabled`, `onValueChangeFinished`.
- New `KSecureField`; `KTextField` gains `onImeAction`.
- `contentDescription` on controls without visible text.

### Fixes
- iOS: callbacks, colors and sizes now update on recomposition; dialogs present from the top-most screen;
  date picker keeps the chosen date; picker reports real selections.
- iOS: controls size from their native size (no clipped switch on iOS 26) and are clipped inside scrolling lists.
- Android: `KDatePicker` opens at `initialDate` and fits small screens; menu `modifier` applies to the button.

### Requirements
- Kotlin 2.4+, Compose Multiplatform 1.12+. Intel iOS simulators (`iosX64`) are no longer supported.

## 1.0

- First release.
