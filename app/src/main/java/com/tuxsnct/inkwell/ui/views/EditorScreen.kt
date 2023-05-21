package com.tuxsnct.inkwell.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import com.tuxsnct.inkwell.ui.components.editor.EditorAppBar
import com.tuxsnct.inkwell.ui.viewmodels.EditorViewModel
import com.tuxsnct.inkwell.utils.CompletePreviews

@Composable
fun EditorScreen(
    popBackStack: () -> Unit,
    editorViewModel: EditorViewModel = hiltViewModel()
) {
    var fileName by remember { mutableStateOf("") }

    editorViewModel.folder.observe(LocalLifecycleOwner.current) {
        fileName = "${it?.type?.name} ${it?.file?.nameWithoutExtension}"
    }

    Scaffold(
        topBar = { EditorAppBar(fileName, popBackStack) }
    ) { contentPadding ->
        Box(modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()) {
            Text(fileName, modifier = Modifier.align(Alignment.Center))
        }
    }
}

@CompletePreviews
@Composable
fun CompactEditorScreenPreview() {
    EditorScreen(false, {})
}

@CompletePreviews
@Composable
fun ExpandedEditorScreenPreview() {
    EditorScreen(true, {})
}