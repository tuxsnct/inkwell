package com.tuxsnct.inkwell.ui.components.editor

import androidx.compose.foundation.Canvas
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        modifier = modifier
    ) {
        with(stylusState) {
            drawOrientation(this.orientation)
            drawTilt(this.tilt)
            drawPressure(this.pressure)
        }
    }
}