# 🚀 Getting Started

## 1. Add the dependency

```kotlin
// build.gradle.kts of your shared module
kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation("io.github.farimarwat:krossui:2.0.0")
        }
    }
}
```

KrossUI needs **Kotlin 2.4+** and **Compose Multiplatform 1.12+**. iOS targets: `iosArm64` and `iosSimulatorArm64`.

## 2. Use the components

Everything is in one package:

```kotlin
import com.farimarwat.krossui.*
```

```kotlin
@Composable
fun SettingsScreen() {
    var name by rememberSaveable { mutableStateOf("") }
    var notifications by rememberSaveable { mutableStateOf(true) }
    var showSaved by remember { mutableStateOf(false) }

    MaterialTheme { // Android components take colors from your theme
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(16.dp)) {
            KTextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = "Name"
            )
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Notifications", Modifier.weight(1f))
                KSwitch(
                    checked = notifications,
                    onCheckedChange = { notifications = it },
                    contentDescription = "Notifications"
                )
            }
            KButton(text = "Save", onClick = { showSaved = true })
        }
    }

    KDialog(
        show = showSaved,
        title = "Saved",
        message = "Your settings were saved.",
        onConfirm = { showSaved = false },
        onDismiss = { showSaved = false }
    )
}
```

On Android this is Material 3; on iOS 26 the button is Liquid Glass, the switch is a `UISwitch`, and the
dialog is a `UIAlertController`.

## 3. Things to know

- **State is yours**: components are *controlled* — pass the value (`checked`, `value`, `selectedIndex`) and
  update it in the callback. Use `rememberSaveable` so it survives rotation.
- **Icons**: give `androidIcon` (a Compose resource `DrawableResource`) and `iosIcon` (an SF Symbol name).
- **Labels**: icon-only controls need a `contentDescription` for screen readers — see [Accessibility](Accessibility).
- **Look**: everything defaults to the native look — see [Native Look](Native-Look) to customize.

← [Back to Home](Home)
