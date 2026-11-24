package com.farimarwat.krossuidemo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

/** Pages of the demo catalog. */
enum class DemoPageId(val title: String, val subtitle: String) {
    Buttons("Buttons", "KButton, KOutlinedButton, KIconButton"),
    TextFields("Text fields", "KTextField, KSecureField"),
    Switch("Switch", "KSwitch"),
    Slider("Slider", "KSlider: continuous, steps, colors"),
    Picker("Picker", "KPicker: wheel, controlled index"),
    DatePicker("Date picker", "KDatePicker with LocalDate"),
    Menu("Menu", "KMenu: text and icon"),
    Dialog("Dialog", "KDialog: alert and confirmation"),
    Progress("Progress", "KProgressIndicator"),
    TabBar("Tab bar", "KTabBar"),
    Layout("Layout", "Wrap, fill and fixed sizes"),
    LongList("Long list", "200 rows of native controls: scrolling and clipping"),
    Performance("Performance", "Scroll benchmark: KrossUI vs Compose Material")
}

/**
 * @param benchmark `"native"` or `"material"`: open the Performance page and run that benchmark
 *   (launch argument, for repeatable measurements).
 */
@Composable
@Preview
fun App(benchmark: String? = null) {
    // Display options for manual testing (null = follow the system)
    var darkOverride by rememberSaveable { mutableStateOf<Boolean?>(null) }
    var rtl by rememberSaveable { mutableStateOf(false) }
    var enabled by rememberSaveable { mutableStateOf(true) }
    var page by rememberSaveable { mutableStateOf(if (benchmark != null) DemoPageId.Performance else null) }

    LaunchedEffect(darkOverride) { applyAppearanceOverride(darkOverride) }
    PlatformBackHandler(enabled = page != null) { page = null }

    val systemDark = isSystemInDarkTheme()
    val dark = darkOverride ?: systemDark
    MaterialTheme(colorScheme = if (dark) darkColorScheme() else lightColorScheme()) {
        CompositionLocalProvider(LocalLayoutDirection provides if (rtl) LayoutDirection.Rtl else LayoutDirection.Ltr) {
            Scaffold(
                topBar = {
                    DemoTopBar(
                        title = page?.title ?: "KrossUI",
                        onBack = if (page != null) ({ page = null }) else null,
                        dark = dark,
                        onDarkChange = { darkOverride = it },
                        rtl = rtl,
                        onRtlChange = { rtl = it },
                        enabled = enabled,
                        onEnabledChange = { enabled = it }
                    )
                }
            ) { padding ->
                Box(Modifier.fillMaxSize().padding(padding)) {
                    val options = DemoOptions(enabled = enabled)
                    // A fresh event log per page
                    val log = remember(page) { EventLog() }
                    when (page) {
                        null -> CatalogHome(onOpen = { page = it })
                        DemoPageId.Buttons -> ButtonsPage(options, log)
                        DemoPageId.TextFields -> TextFieldsPage(options, log)
                        DemoPageId.Switch -> SwitchPage(options, log)
                        DemoPageId.Slider -> SliderPage(options, log)
                        DemoPageId.Picker -> PickerPage(log)
                        DemoPageId.DatePicker -> DatePickerPage(log)
                        DemoPageId.Menu -> MenuPage(log)
                        DemoPageId.Dialog -> DialogPage(log)
                        DemoPageId.Progress -> ProgressPage(log)
                        DemoPageId.TabBar -> TabBarPage(log)
                        DemoPageId.Layout -> LayoutScreen(Modifier.fillMaxSize())
                        DemoPageId.LongList -> LongListScreen(options)
                        DemoPageId.Performance -> PerformanceScreen(autoRun = benchmark)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DemoTopBar(
    title: String,
    onBack: (() -> Unit)?,
    dark: Boolean,
    onDarkChange: (Boolean) -> Unit,
    rtl: Boolean,
    onRtlChange: (Boolean) -> Unit,
    enabled: Boolean,
    onEnabledChange: (Boolean) -> Unit
) {
    Column {
        TopAppBar(
            title = { Text(title) },
            navigationIcon = { if (onBack != null) TextButton(onClick = onBack) { Text("Back") } }
        )
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            FilterChip(selected = dark, onClick = { onDarkChange(!dark) }, label = { Text("Dark") })
            FilterChip(selected = rtl, onClick = { onRtlChange(!rtl) }, label = { Text("RTL") })
            FilterChip(selected = enabled, onClick = { onEnabledChange(!enabled) }, label = { Text("Enabled") })
        }
        HorizontalDivider(Modifier.padding(top = 8.dp))
    }
}

@Composable
private fun CatalogHome(onOpen: (DemoPageId) -> Unit) {
    LazyColumn {
        items(DemoPageId.entries) { page ->
            ListItem(
                headlineContent = { Text(page.title) },
                supportingContent = { Text(page.subtitle) },
                modifier = Modifier.clickable { onOpen(page) }
            )
            HorizontalDivider()
        }
    }
}
