package com.tuxsnct.inkwell.ui.components.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.tuxsnct.inkwell.ui.renderer.FastRenderer
import com.tuxsnct.inkwell.ui.renderer.StylusState
import com.tuxsnct.inkwell.ui.viewmodels.EditorViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun EditorRenderer(
    editorViewModel: EditorViewModel
) {
    var stylusState by remember { mutableStateOf(StylusState()) }
    val fastRenderer = FastRenderer(editorViewModel)
    val lifecycle = LocalLifecycleOwner.current

    LaunchedEffect(lifecycle) {
        lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
            fastRenderer.initialize()
            editorViewModel.stylusState
                .onEach {
                    stylusState = it
                }
                .collect()
        }
    }

    Column {
        StylusVisualization(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            stylusState = stylusState
        )
        Divider(
            thickness = 1.dp,
            color = Color.Black,
        )
        DrawAreaLowLatency(fastRenderer = fastRenderer)
    }
}