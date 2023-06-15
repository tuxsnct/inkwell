package com.tuxsnct.inkwell.model.renderer

import com.tuxsnct.inkwell.ui.renderer.ToolType

class Segment(
    val x1: Float,
    val y1: Float,
    val x2: Float,
    val y2: Float,
    val toolType: ToolType,
    val pressure: Float? = null,
    val tilt: Float? = null,
    val orientation: Float? = null
)