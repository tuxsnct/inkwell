package com.tuxsnct.inkwell.ui.components.manager

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tab
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.tuxsnct.inkwell.ui.MainActivity
import com.tuxsnct.inkwell.utils.CompletePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerAppBar(
    isCompact: Boolean,
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val context = LocalContext.current as MainActivity

    TopAppBar(
        title = {
            Row (
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!isCompact) {
                    Text("InkWell")
                }
                FilledTonalButton(
                    modifier = Modifier.widthIn(max = 500.dp),
                    onClick = { onSearchClick() }
                ) {
                    Icon(
                        Icons.Default.Search,
                        modifier = Modifier.size(18.dp),
                        contentDescription = "Search"
                    )
                    Text("Search", modifier = Modifier.padding(start = 8.dp))
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        },
        actions = {
            Spacer(Modifier.width(8.dp))
            if (!isCompact) {
                IconButton(onClick = { context.openNewIntent() }) {
                    Icon(Icons.Default.Tab, contentDescription = "Tabs")
                }
            }
            IconButton(onClick = { onSettingsClick() }) {
                Icon(Icons.Default.Settings, contentDescription = "Settings")
            }
            Spacer(Modifier.width(8.dp))
        }
    )
}

@CompletePreviews
@Composable
fun CompactManagerAppBarPreview() {
    ManagerAppBar(true, onSearchClick = {}, onSettingsClick = { })
}

@CompletePreviews
@Composable
fun ExpandedManagerAppBarPreview() {
    ManagerAppBar(false, onSearchClick = {}, onSettingsClick = { })
}