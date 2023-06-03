package com.tuxsnct.inkwell.ui.components.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.tuxsnct.inkwell.ui.renderer.FastRenderer
import com.tuxsnct.inkwell.ui.renderer.StylusState

@Composable
fun EditorRenderer(
    fastRenderer: FastRenderer,
    stylusState: StylusState
) {
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