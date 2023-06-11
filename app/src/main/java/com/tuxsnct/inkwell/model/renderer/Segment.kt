package com.tuxsnct.inkwell.model.renderer

class Segment(
    val x1: Float, val y1: Float, val x2: Float, val y2: Float,
    val pressure: Float? = null, val tilt: Float? = null, val orientation: Float? = null,
    val isPredicted: Boolean? = false
)