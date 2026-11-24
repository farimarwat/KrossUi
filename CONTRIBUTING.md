# Contributing to KrossUI

Thanks for helping! Bug reports, docs fixes and new components are all welcome.

## Before you start

- For bigger changes (new components, API changes), open an issue first so we can agree on the API.
- Keep the component pattern: `KXxx.kt` (`expect fun` + KDoc), `KXxxColors.kt`, `KXxxDefaults.kt`,
  `KXxx.android.kt` (Material 3) and `KXxx.ios.kt` (UIKit via the internal `NativeView`).
- Native first: defaults are *unspecified* so each platform keeps its own look; only overrides are applied.

## Build and check

```bash
./gradlew ktlintCheck                         # style (./gradlew ktlintFormat to fix)
./gradlew :krossui:testAndroidHostTest        # Android interaction + screenshot tests
./gradlew :krossui:iosSimulatorArm64Test      # iOS tests (macOS)
./gradlew :krossui:checkKotlinAbi             # public API unchanged?
```

- Changed the public API on purpose? Run `./gradlew :krossui:updateKotlinAbi` and commit `krossui/api/`.
- Changed how a component looks on Android? Run `./gradlew :krossui:recordRoborazziAndroidHostTest` (on macOS)
  and commit `krossui/screenshots/`.
- Try your change in the demo app (see [docs/manual-testing.md](docs/manual-testing.md)).

## Pull requests

- One topic per PR, with a short description of what and why.
- Add or update tests and the component's KDoc.
- Note user-visible changes in [CHANGELOG.md](CHANGELOG.md).
