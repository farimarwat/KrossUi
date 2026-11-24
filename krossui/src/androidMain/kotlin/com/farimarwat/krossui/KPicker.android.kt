package com.farimarwat.krossui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@Composable
public actual fun KPicker(
    items: List<String>,
    onItemPicked: (String) -> Unit,
    modifier: Modifier,
    colors: KPickerColors
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()
    var selectedIndex by remember { mutableStateOf(0) }

    Box(
        modifier = modifier.height(150.dp).background(colors.containerColor),
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
                    color = if (index == selectedIndex) colors.contentColor else colors.disabledContentColor,
                    fontSize = if (index == selectedIndex) 20.sp else 16.sp,
                    modifier = Modifier.padding(vertical = 12.dp).clickable {
                        selectedIndex = index
                        onItemPicked(item)
                        coroutineScope.launch {
                            listState.animateScrollToItem(index)
                        }
                    }
                )
            }
        }

        // Optional: Visual selection overlay (center line)
        Box(
            Modifier.align(Alignment.Center).fillMaxWidth().height(1.dp)
                .background(colors.contentColor.copy(alpha = 0.2f))
        )
    }
}
