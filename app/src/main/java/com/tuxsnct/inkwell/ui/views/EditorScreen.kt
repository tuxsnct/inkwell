package com.tuxsnct.inkwell.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.tuxsnct.inkwell.ui.components.editor.EditorAppBar
import com.tuxsnct.inkwell.ui.components.editor.EditorRenderer
import com.tuxsnct.inkwell.ui.renderer.FastRenderer
import com.tuxsnct.inkwell.ui.renderer.StylusState
import com.tuxsnct.inkwell.ui.viewmodels.EditorViewModel
import com.tuxsnct.inkwell.utils.CompletePreviews
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun EditorScreen(
    isCompact: Boolean,
    popBackStack: () -> Unit,
    editorViewModel: EditorViewModel = hiltViewModel()
) {
    var fileName by remember { mutableStateOf("") }
    var stylusState by remember { mutableStateOf(StylusState()) }
    val fastRenderer = FastRenderer(editorViewModel)
    val lifecycleOwner = LocalLifecycleOwner.current

    LaunchedEffect(lifecycleOwner) {
        editorViewModel.openGlLines.removeAll { true }
        editorViewModel.folder.observe(lifecycleOwner) {
            fileName = "${it.type} ${it.file.nameWithoutExtension}"
        }
        fastRenderer.initialize()
        lifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            editorViewModel.stylusState
                .onEach {
                    stylusState = it
                }
                .collect()
        }
    }

    Scaffold(
        topBar = { EditorAppBar(fileName, popBackStack) }
    ) { contentPadding ->
        Box(modifier = Modifier
            .padding(contentPadding)
            .fillMaxSize()) {
            EditorRenderer(fastRenderer, stylusState)
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