package com.tuxsnct.inkwell.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.tuxsnct.inkwell.ui.components.settings.SettingsAppBar
import com.tuxsnct.inkwell.utils.AllPreviews
import com.tuxsnct.inkwell.utils.PreviewWidthSizeProvider

@Composable
fun SettingsScreen(
    widthSizeClass: WindowWidthSizeClass,
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

@AllPreviews
@Composable
fun SettingsScreenPreview(
    @PreviewParameter(PreviewWidthSizeProvider::class) widthSizeClass: WindowWidthSizeClass
) {
    SettingsScreen(widthSizeClass) {}
}