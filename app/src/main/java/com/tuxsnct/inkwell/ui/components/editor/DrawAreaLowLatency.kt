package com.tuxsnct.inkwell.ui.components.editor

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.tuxsnct.inkwell.ui.renderer.FastRenderer
import com.tuxsnct.inkwell.ui.renderer.LowLatencySurfaceView

@Composable
fun DrawAreaLowLatency(modifier: Modifier = Modifier, fastRenderer: FastRenderer) {
    AndroidView(factory = { context ->
        LowLatencySurfaceView(context, fastRenderer)
    }, modifier = modifier)
}