package com.tuxsnct.inkwell.ui.components.settings

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import com.tuxsnct.inkwell.utils.CompletePreviews

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsAppBar(
    onClickBackIcon: () -> Unit
) {
    LargeTopAppBar(
        title = { Text("Settings") },
        navigationIcon = {
            IconButton(onClickBackIcon) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@CompletePreviews
@Composable
fun SettingsAppBarPreview() {
    SettingsAppBar { }
}