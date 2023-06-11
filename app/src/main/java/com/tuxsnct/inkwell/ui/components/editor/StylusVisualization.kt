package com.tuxsnct.inkwell.ui.components.editor

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import com.tuxsnct.inkwell.ui.renderer.StylusState
import com.tuxsnct.inkwell.ui.renderer.StylusVisualization.drawOrientation
import com.tuxsnct.inkwell.ui.renderer.StylusVisualization.drawPressure
import com.tuxsnct.inkwell.ui.renderer.StylusVisualization.drawTilt

@Composable
fun StylusVisualization(
    modifier: Modifier = Modifier,
    stylusState: StylusState
) {
    Canvas(
        modifier = modifier.background(Color.White).drawBehind { drawRect(Color.White) },
    ) {
        with(stylusState) {
            drawOrientation(this.orientation)
            drawTilt(this.tilt)
            drawPressure(this.pressure)
        }
    }
}