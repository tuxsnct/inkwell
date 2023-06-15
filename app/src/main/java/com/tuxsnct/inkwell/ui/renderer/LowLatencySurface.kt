package com.tuxsnct.inkwell.ui.renderer

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Paint
import android.view.SurfaceView

@SuppressLint("ViewConstructor")
class LowLatencySurfaceView(context: Context, private val fastRenderer: FastRenderer) : SurfaceView(context) {
    private var paint: Paint? = null

    init {
        setOnTouchListener(fastRenderer.onTouchListener)
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        paint = Paint()
        super.setLayerType(LAYER_TYPE_HARDWARE, paint)
        fastRenderer.attachSurfaceView(this, paint!!)
    }

    override fun onDetachedFromWindow() {
        fastRenderer.release()
        paint = null
        super.onDetachedFromWindow()
    }

    override fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        super.onWindowFocusChanged(hasWindowFocus)
        if (hasWindowFocus) fastRenderer.commit()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        fastRenderer.commit()
    }
}