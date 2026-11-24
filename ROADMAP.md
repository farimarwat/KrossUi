# KrossUI Roadmap

Goal: make KrossUI a library that large engineering organisations can adopt with confidence —
stable API, themed, accessible, tested, documented, and broad in components.

Each step is sized to be done in **one working session** and ends with a green build and a PR.
Keep the existing component pattern: `expect fun` + `KXxxColors` + `KXxxDefaults` + platform `actual`s.

---

## Phase 0 — Baseline

### Step 0 · Land the bug fixes
- [ ] Commit the iOS fixes (dialog, date picker, picker, tab bar, text field, buttons, menu, colors) and licence alignment on a branch, open a PR.
- **Done when:** merged to `main`, demo app runs on Android + iOS simulator.

---

## Phase 1 — Foundations (→ release 2.0.0)

### Step 1 · Tooling & CI
- [ ] `explicitApi()` in `krossui`; add explicit `public` modifiers.
- [ ] ktlint + detekt with a checked-in config.
- [ ] ABI validation (Kotlin `abiValidation` / binary-compatibility-validator, incl. klib) with a committed API dump.
- [ ] GitHub Actions: Linux job (Android compile, lint, unit tests) + macOS job (iOS compile, iOS tests).
- [ ] Move library version to `gradle.properties`.
- **Done when:** CI is green on PRs and fails on an unintended API change.

### Step 2 · API 2.0 cleanup
- [ ] Lowercase packages: `com.farimarwat.krossui.button`, `.dialog`, `.picker`, … .
- [ ] Rename `KOutLinedButton` → `KOutlinedButton`, `isEnabled` → `enabled`, `iOSIcon` → `iosIcon`.
- [ ] Parameter order per Compose API guidelines: required → `modifier: Modifier = Modifier` → optional → trailing lambda.
- [ ] `@Immutable` on all `*Colors` classes; corner radius / border as `Dp` instead of `Double`.
- [ ] Keep old names in the old packages as `@Deprecated(ReplaceWith(...))` wrappers/typealiases.
- [ ] Update demo app to the new API.
- **Done when:** demo uses only new API; old API compiles with deprecation warnings.

### Step 3 · Theming (`KrossTheme`)
- [ ] `KrossTheme { }` providing `KrossColors`, `KrossTypography`, `KrossShapes` via `CompositionLocal`.
- [ ] Light + dark schemes; follow system dark mode by default.
- [ ] iOS: resolve semantic UIKit colors (`labelColor`, `systemBackgroundColor`, …) per trait collection; replace hard-coded `PlatformColors`.
- [ ] Android: Material You dynamic color (API 31+) opt-in.
- [ ] `KXxxDefaults.colors()` become `@Composable` and read from the theme.
- [ ] `toUiColor()` handles color spaces correctly.
- **Done when:** demo switches light/dark live on both platforms with no hard-coded colors.

### Step 4 · Native sizing & interop
- [ ] Shared iOS helper: measure UIKit views with `sizeThatFits` inside a custom `Layout` (respects min/max constraints).
- [ ] Remove temporary-view size hacks from all components.
- [ ] Review `UIKitInteropProperties` (interaction mode, placement) for each component.
- [ ] Confirm targets/delegates are retained and callbacks use `rememberUpdatedState` everywhere.
- **Done when:** every component wraps, fills and fixed-sizes correctly in a test screen.

### Step 5 · State holders & types
- [ ] Add `kotlinx-datetime`; date/time APIs use `LocalDate` / `LocalTime`.
- [ ] Hoisted state: `rememberKDatePickerState()`, `rememberKPickerState()` (selected index), `KSlider` steps/range.
- [ ] Split password handling out of `KTextField` into `KSecureField`; add `visualTransformation`/`keyboardOptions` equivalents.
- **Done when:** no component takes raw epoch millis; state survives recomposition and config change.

### Step 6 · Accessibility
- [ ] `contentDescription` / accessibility label params where needed; propagate to UIKit `accessibilityLabel`.
- [ ] Dynamic Type (iOS `UIFontMetrics`) and Android font scale.
- [ ] RTL layout check for every component.
- [ ] Minimum touch targets (44pt / 48dp).
- [ ] Manual pass with TalkBack and VoiceOver; record results in `docs/accessibility.md`.
- **Done when:** every component is operable by screen reader and at the largest text size.

### Step 7 · Tests
- [ ] Replace template tests.
- [ ] `compose-ui-test` in `commonTest` for interaction/state logic.
- [ ] Roborazzi screenshot tests for Android (light/dark, enabled/disabled, LTR/RTL).
- [ ] iOS snapshot/UI tests via XCTest against the demo app.
- [ ] Wire all into CI.
- **Done when:** each component has interaction + screenshot coverage on both platforms.

### Step 8 · Desktop & Web fallbacks
- [ ] Add `jvm()` and `wasmJs()` targets.
- [ ] Pure-Compose (Material 3) `actual` for each component, shared in an intermediate `composeMain` source set where possible.
- [ ] Desktop + web demo entry points.
- **Done when:** a consumer project targeting all four platforms builds.

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
