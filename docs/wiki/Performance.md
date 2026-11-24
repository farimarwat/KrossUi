# Performance

## What costs what

- **Android**: KrossUI components *are* Material 3 composables. Performance is the same as using Material directly.
- **iOS**: every KrossUI component is a real UIKit view hosted in Compose (`UIKitView`, placed above the
  Compose canvas so Liquid Glass works). Creating a UIKit view is more expensive than drawing a Compose node,
  so the cost shows up when many native controls are *created* quickly — e.g. flinging a long list.
  While on screen, native controls cost about the same as Compose ones.

## Measurements

The demo app's **Performance** page scrolls a 500-row list (each row: text + switch + button) by itself for
360 frames and reports frames per second, slow frames (> 1.5× a normal frame) and the worst frame.
Rows can be KrossUI controls or plain Compose Material controls.

iOS 26.1 simulator (iPhone 17 Pro, release build, 60 Hz):

| Scroll speed | KrossUI (native UIKit) | Compose Material |
|---|---|---|
| Fast fling (≈ 30 pt per frame) | 60 fps · 0 slow frames · worst 17 ms | 60 fps · 0 slow frames · worst 17 ms |
| Stress (≈ 100 pt per frame, several new rows every frame) | 60 fps · 2–3 slow frames of 360 (< 1%) · worst 39 ms | 60 fps · 0 slow frames · worst 17 ms |

Android: identical code path (Material); emulator numbers are too noisy to compare (47–59 fps for the same code).

The simulator runs on the Mac's CPU and is capped at 60 Hz, so **measure on a real device** before drawing
conclusions for your app (ProMotion iPhones run at 120 Hz):

- **iOS**: in Xcode, *Edit Scheme → Run → Arguments*, add `-benchmark native` (or `material`), build **Release**, run.
- **Android**: `./gradlew :androidApp:installRelease`, then
  `adb shell am start -n com.farimarwat.krossuidemo/.MainActivity -e benchmark native` and read
  `adb logcat | grep "KrossUI benchmark"`.

## Guidance

- Use KrossUI controls wherever users interact with them — forms, settings, toolbars, dialogs, normal lists.
- In very long, fast-scrolling lists on iOS, keep native controls per row low: use Compose `Text`/`Image`
  for content and KrossUI only for the interactive control (one switch or button per row is fine).
- Prefer stable `key`s in `LazyColumn` so rows are not recreated unnecessarily.
- KrossUI already avoids per-frame UIKit work while scrolling (clipping is only updated when it changes).

## Possible future work

Compose can reuse UIKit views in lazy lists (`UIKitView(onReset = …)`), which would remove the creation cost
in the stress case. It needs every component to keep its view, targets and delegates reusable; not worth the
complexity for < 1% slow frames today. Revisit if device measurements show a problem.

← [Back to Home](Home)
