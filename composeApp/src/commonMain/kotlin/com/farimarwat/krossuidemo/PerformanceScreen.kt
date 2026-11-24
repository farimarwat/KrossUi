package com.farimarwat.krossuidemo

import androidx.compose.foundation.gestures.scrollBy
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.FilterChip
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.KButton
import com.farimarwat.krossui.KSwitch
import kotlin.math.roundToInt
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val ROWS = 500
private const val SCROLL_FRAMES = 360
private const val SCROLL_PX_PER_FRAME = 90f

/**
 * Scroll benchmark: scrolls a 500-row list by itself and measures frame times.
 * Compare KrossUI rows (native UIKit controls on iOS) with plain Compose Material rows.
 */
@Composable
fun PerformanceScreen(autoRun: String? = null) {
    var nativeRows by remember { mutableStateOf(autoRun != "material") }
    var running by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf("Tap Run. The list scrolls by itself for ~6 seconds.") }
    val listState = rememberLazyListState()
    val scope = rememberCoroutineScope()
    val run: () -> Unit = {
        scope.launch {
            running = true
            val label = if (nativeRows) "KrossUI" else "Compose Material"
            result = "$label: running…"
            result = "$label: " + measureScroll(listState)
            println("KrossUI benchmark — $result")
            running = false
        }
    }
    // Launch-argument benchmark: wait for the list to settle, then run once
    LaunchedEffect(autoRun) {
        if (autoRun != null) {
            delay(1500)
            run()
        }
    }

    Column(Modifier.fillMaxSize()) {
        Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
                FilterChip(selected = nativeRows, onClick = { nativeRows = true }, label = { Text("KrossUI") })
                FilterChip(selected = !nativeRows, onClick = {
                    nativeRows = false
                }, label = { Text("Compose Material") })
                Button(
                    enabled = !running,
                    onClick = run
                ) { Text("Run") }
            }
            Text(result, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.primary)
        }
        HorizontalDivider()
        LazyColumn(state = listState, modifier = Modifier.fillMaxSize()) {
            items((1..ROWS).toList(), key = { it }) { row ->
                if (nativeRows) KrossUiRow(row) else MaterialRow(row)
                HorizontalDivider()
            }
        }
    }
}

/** Scrolls at a constant speed and returns frame statistics. */
private suspend fun measureScroll(listState: LazyListState): String {
    listState.scrollToItem(0)
    val frameNanos = ArrayList<Long>(SCROLL_FRAMES)
    var previous = withFrameNanos { it }
    repeat(SCROLL_FRAMES) {
        listState.scrollBy(SCROLL_PX_PER_FRAME)
        val now = withFrameNanos { it }
        frameNanos += now - previous
        previous = now
    }
    val sorted = frameNanos.sorted()
    val median = sorted[sorted.size / 2]
    val totalSeconds = frameNanos.sum() / 1e9
    val fps = frameNanos.size / totalSeconds
    // A frame is slow when it took clearly longer than a normal frame on this display (60 or 120 Hz)
    val slow = frameNanos.count { it > median * 1.5 }
    val worstMs = sorted.last() / 1e6
    return "${fps.roundToInt()} fps · slow frames ${slow * 100 / frameNanos.size}% ($slow/${frameNanos.size}) · " +
        "worst ${worstMs.roundToInt()} ms"
}

@Composable
private fun KrossUiRow(row: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Row $row", modifier = Modifier.weight(1f))
        KSwitch(checked = row % 2 == 0, onCheckedChange = {})
        KButton(text = "Open", onClick = {})
    }
}

@Composable
private fun MaterialRow(row: Int) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Row $row", modifier = Modifier.weight(1f))
        Switch(checked = row % 2 == 0, onCheckedChange = {})
        Button(onClick = {}) { Text("Open") }
    }
}
