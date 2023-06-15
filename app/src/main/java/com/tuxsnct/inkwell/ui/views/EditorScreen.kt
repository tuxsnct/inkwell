package com.tuxsnct.inkwell.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.zIndex
import androidx.hilt.navigation.compose.hiltViewModel
import com.tuxsnct.inkwell.ui.components.editor.DrawAreaLowLatency
import com.tuxsnct.inkwell.ui.components.editor.EditorToolBar
import com.tuxsnct.inkwell.ui.renderer.FastRenderer
import com.tuxsnct.inkwell.ui.renderer.ToolType
import com.tuxsnct.inkwell.ui.viewmodels.EditorViewModel
import com.tuxsnct.inkwell.utils.AllPreviews
import com.tuxsnct.inkwell.utils.PreviewWidthSizeProvider

@Composable
fun EditorScreen(
    widthSizeClass: WindowWidthSizeClass,
    editorViewModel: EditorViewModel = hiltViewModel()
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    var isInitialized by rememberSaveable { mutableStateOf(false) }
    val fastRenderer by remember { mutableStateOf(FastRenderer(editorViewModel)) }
    var folderName by remember { mutableStateOf("") }
    var toolType by remember { mutableStateOf(ToolType.PEN) }

    LaunchedEffect(Unit) {
        if (!isInitialized) {
            editorViewModel.canvasLines.clear()
            isInitialized = true
        }
        editorViewModel.folderName.observe(lifecycleOwner) { folderName = it }
        editorViewModel.toolType.observe(lifecycleOwner) { toolType = it }
        fastRenderer.commit()
    }

    Scaffold { contentPadding ->
        EditorToolBar(
            modifier = Modifier.zIndex(1F),
            widthSizeClass = widthSizeClass,
            title = folderName,
            toolType = toolType,
            onToolTypeButtonClick = { editorViewModel.changeToolType(it) }
        )
        Box(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
        ) {
            DrawAreaLowLatency(modifier = Modifier, fastRenderer = fastRenderer)
        }
    }
}

@AllPreviews
@Composable
fun EditorScreenPreview(
    @PreviewParameter(PreviewWidthSizeProvider::class) widthSizeClass: WindowWidthSizeClass
) {
    EditorScreen(widthSizeClass)
}