package com.tuxsnct.inkwell.ui.components.manager

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tab
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.tuxsnct.inkwell.ui.MainActivity
import com.tuxsnct.inkwell.utils.AllPreviews
import com.tuxsnct.inkwell.utils.PreviewWidthSizeProvider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManagerAppBar(
    widthSizeClass: WindowWidthSizeClass,
    onSearchClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val context = LocalContext.current

    TopAppBar(
        title = {
            Row (
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (widthSizeClass != WindowWidthSizeClass.Compact) {
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
            if (widthSizeClass != WindowWidthSizeClass.Compact) {
                IconButton(onClick = { (context as MainActivity).openNewIntent() }) {
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

@AllPreviews
@Composable
fun ManagerAppBarPreview(
    @PreviewParameter(PreviewWidthSizeProvider::class) widthSizeClass: WindowWidthSizeClass
) {
    ManagerAppBar(widthSizeClass, onSearchClick = {}, onSettingsClick = { })
}
