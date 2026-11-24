package com.farimarwat.krossuidemo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.farimarwat.krossui.KButton
import com.farimarwat.krossui.KSwitch

/**
 * 200 rows of native controls: check that scrolling stays smooth and that controls are clipped
 * at the top and bottom edges (never drawn over the bars).
 */
@Composable
fun LongListScreen(options: DemoOptions) {
    val checked = remember { mutableStateMapOf<Int, Boolean>() }
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        item {
            Text(
                "Scroll fast: should stay smooth; controls must not draw outside the list.",
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.bodySmall
            )
        }
        items((1..200).toList(), key = { it }) { row ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Row $row", modifier = Modifier.weight(1f))
                KSwitch(
                    checked = checked[row] ?: false,
                    onCheckedChange = { checked[row] = it },
                    enabled = options.enabled,
                    contentDescription = "Row $row"
                )
                KButton(text = "Open", onClick = {}, enabled = options.enabled)
            }
            HorizontalDivider()
        }
    }
}
