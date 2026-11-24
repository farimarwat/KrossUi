package com.farimarwat.krossui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.takeOrElse
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
public actual fun KPicker(
    items: List<String>,
    selectedIndex: Int,
    onSelectedIndexChange: (Int) -> Unit,
    modifier: Modifier,
    contentDescription: String?,
    colors: KPickerColors
) {
    val listState = rememberLazyListState()
    // Keep the selected row in view when the selection changes
    LaunchedEffect(selectedIndex) {
        if (selectedIndex in items.indices) listState.animateScrollToItem(selectedIndex)
    }
    // Unspecified colors follow the app's MaterialTheme
    val selectedColor = colors.contentColor.takeOrElse { MaterialTheme.colorScheme.primary }
    val otherColor = colors.disabledContentColor.takeOrElse { MaterialTheme.colorScheme.onSurfaceVariant }

    Box(
        modifier = modifier.labelled(contentDescription).height(150.dp).background(
            colors.containerColor.takeOrElse {
                Color.Transparent
            }
        ),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            itemsIndexed(items) { index, item ->
                Text(
                    text = item,
                    color = if (index == selectedIndex) selectedColor else otherColor,
                    fontSize = if (index == selectedIndex) 20.sp else 16.sp,
                    modifier = Modifier
                        .heightIn(min = 48.dp)
                        .selectable(
                            selected = index == selectedIndex,
                            onClick = { onSelectedIndexChange(index) },
                            role = Role.RadioButton
                        )
                        .wrapContentHeight()
                )
            }
        }

        // Optional: Visual selection overlay (center line)
        Box(
            Modifier.align(Alignment.Center).fillMaxWidth().height(1.dp)
                .background(selectedColor.copy(alpha = 0.2f))
        )
    }
}
