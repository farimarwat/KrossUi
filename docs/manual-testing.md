# Manual testing with the demo app

The demo app (`composeApp` + `androidApp` + `iosApp`) is a catalog: one page per component.
Every page shows the component's variants, its live state, and an **Events** log that lists each
callback as it fires.

## Run it

- **Android (device or emulator):** run the `androidApp` configuration in Android Studio,
  or `./gradlew :androidApp:installDebug`.
- **iOS simulator:** open `iosApp/iosApp.xcodeproj` in Xcode, pick a simulator (iOS 26 for Liquid Glass,
  iOS 18 for the classic look) and press Run.
- **iPhone:** in Xcode select your device and set your Team under *Signing & Capabilities*.

## Display options (top of every page)

| Chip | What it does |
|---|---|
| **Dark** | Forces dark mode for the app (iOS: native controls switch too). |
| **RTL** | Lays out right-to-left; native controls must mirror. |
| **Enabled** | Turns every control on the page off/on; disabled controls must look dimmed and ignore taps. |

Text size: change it in the phone's settings (Android *Font size*, iOS *Larger Text*) and reopen a page.

## Checklist

| Page | Check |
|---|---|
| Buttons | Each tap logs `onClick`. iOS 26: glass capsules; iOS 18: filled / bordered. Icon-only button is read as "Favourite" by the screen reader. Custom colors override the native look. |
| Text fields | Typing updates `value`. Done logs `onImeAction` and closes the keyboard. *Amount* opens a number pad. Read-only can't be edited. Secure field hides characters. |
| Switch | Toggle logs `onCheckedChange`; state text follows. iOS: system green. Orange variant uses the custom color. |
| Slider | Dragging updates the value; releasing logs "finished". Steps slider snaps to 0/20/40/60/80/100. |
| Picker | Scrolling or tapping updates "selected" and logs. *Next fruit* moves the wheel (controlled from code). |
| Date picker | Opens on today (then on the last picked date). Done logs the date; Cancel logs `onDismiss`. Fits the screen in landscape. |
| Menu | Opens a native menu; picking logs the item; the third item is disabled. Icon menu is read as "More options". |
| Dialog | Alert: OK logs `onConfirm` + `onDismiss`. Confirmation: Delete / Cancel log their callbacks. |
| Progress | Three spinners animate: default size, larger, green. |
| Tab bar | Tapping tabs logs `onTabClick`; iOS 26 shows the floating glass bar. |
| Layout | Each control wrapped, full width and fixed size; tinted area = the control's bounds. |
| Long list | Scroll fast through 200 rows: smooth, and switches/buttons never draw over the top bar. |

**Every page:** repeat with **Dark**, **RTL**, **Enabled off** and large text.
**Screen readers:** see [accessibility.md](accessibility.md) for the TalkBack / VoiceOver pass.
