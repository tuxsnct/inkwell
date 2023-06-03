package com.tuxsnct.inkwell.ui.renderer

import android.annotation.SuppressLint
import android.content.Context
import android.view.SurfaceView

@SuppressLint("ViewConstructor")
class LowLatencySurfaceView(context: Context, private val fastRenderer: FastRenderer) : SurfaceView(context) {
    init {
        setOnTouchListener(fastRenderer.onTouchListener)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        fastRenderer.attachSurfaceView(this)
    }

    override fun onDetachedFromWindow() {
        fastRenderer.release()
        super.onDetachedFromWindow()
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        fastRenderer.onWindowFocusChanged(hasWindowFocus)
    }
}