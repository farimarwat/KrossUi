package com.farimarwat.krossuidemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/** Live list of callbacks fired on a page, newest first, so manual testing shows each event. */
class EventLog {
    val entries = mutableStateListOf<String>()

    fun log(message: String) {
        entries.add(0, message)
        if (entries.size > 12) entries.removeAt(entries.lastIndex)
    }
}

/** Display options shared by every page. */
data class DemoOptions(val enabled: Boolean)

/** A scrolling page: sections on top, the event log at the bottom. */
@Composable
fun DemoPage(log: EventLog, content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        content()
        EventLogView(log)
    }
}

/** A titled group of examples with a one-line explanation of what to check. */
@Composable
fun Section(title: String, check: String, content: @Composable ColumnScope.() -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        Text(title, style = MaterialTheme.typography.titleMedium, color = MaterialTheme.colorScheme.onBackground)
        Text(check, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        content()
    }
}

/** Shows live state, e.g. "value = 0.42". */
@Composable
fun StateText(text: String) {
    Text(text, style = MaterialTheme.typography.labelLarge, color = MaterialTheme.colorScheme.primary)
}

@Composable
private fun EventLogView(log: EventLog) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(Modifier.padding(12.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
            Text("Events", style = MaterialTheme.typography.titleSmall)
            HorizontalDivider()
            if (log.entries.isEmpty()) {
                Text(
                    "Interact with a component — its callbacks appear here.",
                    style = MaterialTheme.typography.bodySmall
                )
            }
            log.entries.forEach { Text(it, style = MaterialTheme.typography.bodySmall) }
        }
    }
}
