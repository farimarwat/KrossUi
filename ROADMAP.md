# KrossUI Roadmap

Goal: make KrossUI a library that large engineering organisations can adopt with confidence —
stable API, themed, accessible, tested, documented, and broad in components.

Each step is sized to be done in **one working session** and ends with a green build and a PR.
Keep the existing component pattern: `expect fun` + `KXxxColors` + `KXxxDefaults` + platform `actual`s.

---

## Phase 0 — Baseline

### Step 0 · Land the bug fixes
- [x] Commit the iOS fixes (dialog, date picker, picker, tab bar, text field, buttons, menu, colors) and licence alignment on a branch.
- [x] Toolchain upgrade (Kotlin 2.4, Compose Multiplatform 1.12, AGP 9, Gradle 9) and demo split into `androidApp` + `composeApp`.
- [x] Native iOS 26 Liquid Glass for iOS components; classic native look on earlier iOS.
- **Done when:** merged to `main`, demo app runs on Android + iOS simulator.

---

## Phase 1 — Foundations (→ release 2.0.0)

### Step 1 · Tooling & CI
- [x] `explicitApi()` in `krossui`; add explicit `public` modifiers.
- [x] ktlint with a checked-in config (`.editorconfig`).
- [x] ABI validation (Kotlin built-in `abiValidation`, incl. klib) with a committed API dump in `krossui/api/`.
- [x] GitHub Actions: Linux job (lint, Android build) + macOS job (iOS build, API check). Tests are added in Step 7.
- [x] Move library version to `gradle.properties` (`VERSION_NAME`).
- **Done when:** CI is green on PRs and fails on an unintended API change.

### Step 2 · API 2.0 cleanup
- [x] One package: `com.farimarwat.krossui` (files flat in one folder).
- [x] Rename `KOutLinedButton` → `KOutlinedButton`, `isEnabled` → `enabled`, `iOSIcon` → `iosIcon`.
- [x] Parameter order per Compose API guidelines: required → `modifier: Modifier = Modifier` → optional → trailing lambda.
- [x] `@Immutable` on all `*Colors` classes; corner radius / border / size as `Dp`; `KTextField` uses `PaddingValues`.
- [x] Keep old names in the old packages as `@Deprecated(ReplaceWith(...))` wrappers/typealiases (`compat/`), documented in `MIGRATION.md`.
- [x] Update demo app to the new API.
- **Done when:** demo uses only new API; old API compiles with deprecation warnings.

### Step 3 · Native-first defaults (replaces the planned `KrossTheme`)
- [x] Every default (colors, font size, corner radius, border width, size, padding) is *unspecified* = platform default.
- [x] Android: unspecified values resolve from the app's `MaterialTheme` (dark mode, Material You, brand colors).
- [x] iOS: unspecified values keep UIKit's system appearance (dark mode, tint, Liquid Glass); buttons use native
      configurations (glass on 26+, filled/bordered on 15–25, classic on 14).
- [x] Android `KTextField` is Material 3's outlined text field.
- [x] `toUiColor()` converts to sRGB.
- **Done:** demo has no hard-coded colors and follows light/dark on both platforms (verified iOS 26, iOS 18, Android).

### Step 4 · Native sizing & interop
- [x] Shared iOS `NativeView`: sizes UIKit views from their intrinsic size inside a Compose `layout`
      (respects wrap / fill / fixed constraints and the 44pt touch target).
- [x] Removed temporary-view and hard-coded size hacks (buttons, switch, slider, menu, picker, text field, tab bar).
- [x] Interop properties: cooperative touches, native accessibility on, overlay placement; overlay views are
      clipped when Compose clips them (e.g. scrolled out of a list), unclipped while fully visible (glass halos).
- [x] Targets/delegates retained; callbacks read through `rememberUpdatedState`; tab bar items/selection applied synchronously.
- **Done:** demo "Layout" tab shows every component wrapped, filling and fixed-size (verified iOS 26 and iOS 18).

### Step 5 · State & types
- [x] `kotlinx-datetime`: `KDatePicker` uses `LocalDate` (optional `initialDate`, `null` = today); no raw epoch millis.
- [x] Controlled components instead of extra state classes: `KPicker(selectedIndex, onSelectedIndexChange)`;
      state lives in the app (`rememberSaveable` survives configuration changes).
- [x] `KSlider`: `valueRange`, `steps`, `enabled`, `onValueChangeFinished` (iOS snaps to steps).
- [x] `KSecureField` for passwords; `KTextField.onImeAction`.
- [x] 1.x overloads kept in `compat/`; unit tests for step snapping and date conversion (Android + iOS) run in CI.
- **Done:** no component takes epoch millis; all state is hoisted to the caller.

### Step 6 · Accessibility
- [x] `contentDescription` on controls without visible text; Android semantics, iOS `accessibilityLabel`
      (native accessibility enabled for UIKit views).
- [x] Text size: Android `sp`; iOS Dynamic Type (`UIFontMetrics` for custom sizes, live updates in text fields).
- [x] RTL: iOS native views follow Compose's layout direction; Android via Compose.
- [x] Touch targets: 44pt iOS (buttons, menu, slider, fields); Android `KPicker` rows 48dp, selectable radio items.
- [x] `docs/accessibility.md` with verified results (uiautomator labels, 2× font, Dynamic Type XXXL, iOS RTL).
- [ ] Manual TalkBack / VoiceOver walk-through on devices (user).

### Step 7 · Tests
- [x] Template tests replaced; broken Android device-test setup removed (Robolectric covers it).
- [x] Android interaction tests (Robolectric + compose-ui-test): click, toggle, slider progress, text input,
      password semantics, picker selection, menu, dialog, date picker, labels.
- [x] Android screenshot tests (Roborazzi): every component in light, dark, RTL and disabled; references in
      `krossui/screenshots`, verified in CI (macOS job, same renderer as local recording).
- [x] iOS tests on the simulator: native sizing rules, button styling, color conversion, date round trips.
- [x] All tests wired into CI.
- [ ] iOS UI / screenshot tests of the rendered UIKit controls (XCUITest target in the demo app) — later;
      Compose UI tests cannot host UIKit interop views.
- **Found by the tests:** the Android date picker's custom popup could push "Done" off small screens →
  now Material's `DatePickerDialog`.

### Step 9 · Performance
- [ ] Benchmarks: `LazyColumn` with 500 rows of interop components vs. pure Compose.
- [ ] Publish numbers and guidance ("native vs. compose mode") in docs.
- [ ] Optional `KrossTheme(renderMode = Native | Compose)` switch if the cost is significant.
- **Done when:** numbers are documented and no component regresses scrolling noticeably.

### Step 10 · Documentation
- [ ] Dokka API reference published to GitHub Pages.
- [ ] Docs site: getting started, theming, every component with Android + iOS screenshots, migration 1.x → 2.0.
- [ ] Compatibility matrix (Kotlin, Compose MP, AGP, min iOS / Android).
- [ ] `CHANGELOG.md`, `CONTRIBUTING.md`, `SECURITY.md`, `CODE_OF_CONDUCT.md`, issue/PR templates.
- **Done when:** a new user can install and use any component from the docs alone.

### Step 11 · Release 2.0.0
- [ ] Final API review against ABI dump; changelog; tag; publish to Maven Central.
- **Done when:** `io.github.farimarwat:krossui:2.0.0` is live and docs point to it.

---

## Phase 2 — Components (→ 2.1, 2.2, …)

Each new component ships with: common API + Colors + Defaults, Android/iOS/Desktop/Web actuals,
theme support, accessibility, tests, screenshots, docs page, demo screen.

### Desktop & Web fallbacks (moved from Phase 1, step 8)
- [ ] Add `jvm()` and `wasmJs()` targets.
- [ ] Pure-Compose (Material 3) `actual` for each component, shared in an intermediate `composeMain` source set where possible.
- [ ] Desktop + web demo entry points.
- **Done when:** a consumer project targeting all four platforms builds.

| Step | Components | iOS | Android |
|---|---|---|---|
| 12 | `KSegmentedControl`, `KCheckbox` / `KToggle` | `UISegmentedControl`, adaptive toggle | `SegmentedButton`, `Checkbox` |
| 13 | `KTimePicker`, `KDateRangePicker` | `UIDatePicker` (time / ranges) | `TimePicker`, `DateRangePicker` |
| 14 | `KBottomSheet`, `KActionSheet` | `UISheetPresentationController`, action-sheet alert | `ModalBottomSheet` |
| 15 | `KTopBar`, `KScaffold` | `UINavigationBar` (large titles) | `TopAppBar`, `Scaffold` |
| 16 | `KSearchBar`, `KLinearProgress`, `KPullToRefresh` | `UISearchBar`, `UIProgressView`, `UIRefreshControl` | `SearchBar`, `LinearProgressIndicator`, `PullToRefreshBox` |
| 17 | `KShareSheet`, `KHaptics` | `UIActivityViewController`, `UIFeedbackGenerator` | share intent, `HapticFeedback` |
| 18 | `KPhotoPicker` | `PHPickerViewController` | Photo Picker |
| 19 | `KContextMenu`, `KAlert` (text input), `KStepper` | `UIContextMenuInteraction`, alert text fields, `UIStepper` | dropdown/long-press menu, dialog, custom stepper |
| 20 | `KListSection` / `KListItem`, `KSnackbar`, `KBadge`, `KCard`, `KDivider` | inset-grouped list style | Material equivalents |

Release cadence: 2.1.0 after steps 12–14, 2.2.0 after 15–17, 2.3.0 after 18–20.
