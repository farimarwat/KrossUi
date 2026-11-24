# Accessibility

KrossUI renders native controls, so screen readers, text scaling and right-to-left layout use each
platform's own accessibility support. This page lists what KrossUI adds and what has been verified.

## Labels

Controls without visible text take an optional `contentDescription`, read by TalkBack / VoiceOver:

| Component | Label to give |
|---|---|
| `KIconButton` | Required when there is no `title` (icon only). |
| `KMenu` | When only an icon is shown (defaults to `title`). |
| `KSwitch`, `KSlider`, `KPicker` | What the control changes, e.g. "Notifications", "Volume". |
| `KProgressIndicator` | What is loading (iOS reads "In progress" by default). |
| `KTextField`, `KSecureField` | Optional; the placeholder is read when no label is given. |

Buttons with text, tab bar items, menu items and dialogs are read from their visible text.

- **Android**: the label is added as Compose semantics (`contentDescription`).
- **iOS**: native accessibility is enabled for every UIKit view, so VoiceOver reads the native control
  (its role, value and state) and the label is set as the view's `accessibilityLabel`.
  Without a label, an icon-only button would be read as its SF Symbol name.

## Text size

- **Android**: font sizes are `sp` and follow the system font scale.
- **iOS**: default text uses the system's Dynamic Type styles; a custom `fontSize` is scaled with
  `UIFontMetrics`, and text fields update live when the user changes the text size.

## Right-to-left

- **Android**: Compose and Material handle RTL; KrossUI has no direction-specific code.
- **iOS**: native views follow Compose's `LayoutDirection` (also when a screen forces RTL);
  text-field padding is resolved for the current direction.

## Touch targets

- **Android**: Material components enforce 48dp; `KPicker` rows are at least 48dp and are exposed as
  selectable radio items.
- **iOS**: buttons, menu, slider and text fields are at least 44pt (Human Interface Guidelines).
  `KSwitch` keeps UIKit's native size.

## Verification

| Check | Android | iOS |
|---|---|---|
| Labels present in the accessibility tree | ✅ `uiautomator dump` (emulator) | — |
| Large text | ✅ font scale 2.0 | ✅ Dynamic Type XXXL (iOS 26.1) |
| Right-to-left | relies on Compose (forced-RTL setting not applied on emulator) | ✅ `-NSForceRightToLeftWritingDirection` (iOS 26.1) |
| TalkBack / VoiceOver walk-through | ⏳ manual pass needed | ⏳ manual pass needed |

**Manual pass to do on devices:** turn on TalkBack / VoiceOver, swipe through the demo app's
*Components* and *Layout* tabs, and confirm every control is announced with its label, role and
state (e.g. "Notifications, switch, on"), and that sliders and pickers can be adjusted with swipe up/down.

← [Back to Home](Home)
