package com.tuxsnct.inkwell.ui.renderer

import android.graphics.Path


data class StylusState(
    var pressure: Float = 0F,
    var orientation: Float = 0F,
    var tilt: Float = 0F,
    var path: Path = Path(),
)