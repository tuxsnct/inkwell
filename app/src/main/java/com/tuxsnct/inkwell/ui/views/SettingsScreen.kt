package com.tuxsnct.inkwell.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.tuxsnct.inkwell.ui.components.settings.SettingsAppBar
import com.tuxsnct.inkwell.utils.CompletePreviews

@Composable
fun SettingsScreen(
    isCompact: Boolean,
    onClickBack: () -> Unit
) {
    Scaffold(
        topBar = { SettingsAppBar { onClickBack() } }
    ) { contentPadding ->
        Box(modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()) {
            Text("Settings", modifier = Modifier.align(Alignment.Center))
        }
    }
}

@CompletePreviews
@Composable
fun CompactSettingsScreenPreview() {
    SettingsScreen(true) {}
}

@CompletePreviews
@Composable
fun ExpandedSettingsScreenPreview() {
    SettingsScreen(false) {}
}