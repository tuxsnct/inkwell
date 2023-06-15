package com.tuxsnct.inkwell.ui.renderer

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BlendMode
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import androidx.graphics.lowlatency.CanvasFrontBufferedRenderer
import com.tuxsnct.inkwell.model.renderer.Segment
import com.tuxsnct.inkwell.ui.viewmodels.EditorViewModel
import kotlin.math.pow

enum class ToolType {
    PEN,
    // MARKER,
    HIGHLIGHTER,
    ERASER,
    // LASER_POINTER
}

class FastRenderer(
    private var editorViewModel: EditorViewModel
) : CanvasFrontBufferedRenderer.Callback<Segment> {
    private var paint: Paint? = null
    private var backgroundBitmap: Bitmap? = null

    private var frontBufferRenderer: CanvasFrontBufferedRenderer<Segment>? = null

    private var previousX: Float = 0f
    private var previousY: Float = 0f
    private var currentX: Float = 0f
    private var currentY: Float = 0f

    private fun setPaint(paint: Paint, param: Segment) {
        when (param.toolType) {
            ToolType.PEN -> {
                paint.strokeWidth = 2.0f + 6.0f * (param.pressure ?: 1.0f).pow(2)
                paint.style = Paint.Style.STROKE
                paint.strokeCap = Paint.Cap.ROUND
                paint.strokeJoin = Paint.Join.ROUND
                paint.color = Color.BLACK
                paint.alpha = 255
                paint.blendMode = null
            }
            /*
            ToolType.MARKER -> {
                paint.strokeWidth = 8.0f
                paint.style = Paint.Style.STROKE
                paint.strokeCap = Paint.Cap.ROUND
                paint.strokeJoin = Paint.Join.ROUND
                paint.color = Color.BLACK
                paint.alpha = 255
                paint.blendMode = null
            }
             */
            ToolType.HIGHLIGHTER -> {
                paint.strokeWidth = 30.0f
                paint.style = Paint.Style.STROKE
                paint.strokeCap = Paint.Cap.SQUARE
                paint.strokeJoin = Paint.Join.MITER
                paint.color = Color.parseColor("#FFFF00")
                paint.alpha = 32
                paint.blendMode = null
            }
            ToolType.ERASER -> {
                paint.strokeWidth = 50.0f
                paint.style = Paint.Style.FILL
                paint.strokeCap = Paint.Cap.ROUND
                paint.strokeJoin = Paint.Join.ROUND
                paint.color = Color.TRANSPARENT
                paint.alpha = 255
                paint.blendMode = BlendMode.CLEAR
            }
            /*
            ToolType.LASER_POINTER -> {
                paint.strokeWidth = 2.0f + 8.0f * (param.pressure ?: 1.0f)
                paint.style = Paint.Style.STROKE
                paint.strokeCap = Paint.Cap.ROUND
                paint.strokeJoin = Paint.Join.ROUND
                paint.color = Color.RED
                paint.alpha = 255
                paint.blendMode = null
            }
             */
        }
    }

    override fun onDrawFrontBufferedLayer(
        canvas: Canvas,
        bufferWidth: Int,
        bufferHeight: Int,
        param: Segment
    ) {
        paint?.let {
            setPaint(it, param)
            canvas.drawLine(param.x1, param.y1, param.x2, param.y2, it)
        }
    }

    override fun onDrawMultiBufferedLayer(
        canvas: Canvas,
        bufferWidth: Int,
        bufferHeight: Int,
        params: Collection<Segment>
    ) {
        editorViewModel.canvasLines.add(params.toList())

        if (backgroundBitmap == null) {
            backgroundBitmap = Bitmap.createBitmap(bufferWidth, bufferHeight, Bitmap.Config.ARGB_8888).apply {
                val backgroundBitmapCanvas = Canvas(this)
                backgroundBitmapCanvas.drawColor(Color.WHITE)
            }
        }

        backgroundBitmap?.let { canvas.drawBitmap(it, 0f, 0f, null) }

        paint?.let {
            canvas.saveLayer(null, null)
            for (line in editorViewModel.canvasLines) {
                for (segment in line) {
                    setPaint(it, segment)
                    canvas.drawLine(segment.x1, segment.y1, segment.x2, segment.y2, it)
                }
            }
            canvas.restore()
        }
    }

    fun attachSurfaceView(surfaceView: SurfaceView, paint: Paint) {
        this.paint = paint
        frontBufferRenderer = CanvasFrontBufferedRenderer(surfaceView, this)
    }

    fun release() {
        frontBufferRenderer?.release(true)
    }

    fun commit() = frontBufferRenderer?.commit()

    @SuppressLint("ClickableViewAccessibility")
    val onTouchListener = View.OnTouchListener { view, event ->
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                view.requestUnbufferedDispatch(event)

                currentX = event.x
                currentY = event.y

                val segment = editorViewModel.toolType.value?.let {
                    Segment(currentX, currentY, currentX, currentY, it, event.pressure,
                        event.getAxisValue(MotionEvent.AXIS_TILT), event.getAxisValue(MotionEvent.AXIS_ORIENTATION))
                }

                segment?.let { frontBufferRenderer?.renderFrontBufferedLayer(it) }
            }
            MotionEvent.ACTION_MOVE -> {
                previousX = currentX
                previousY = currentY
                currentX = event.x
                currentY = event.y

                val segment = editorViewModel.toolType.value?.let {
                    Segment(previousX, previousY, currentX, currentY, it, event.pressure,
                        event.getAxisValue(MotionEvent.AXIS_TILT), event.getAxisValue(MotionEvent.AXIS_ORIENTATION))
                }

                segment?.let { frontBufferRenderer?.renderFrontBufferedLayer(it) }
            }
            MotionEvent.ACTION_UP -> {
                frontBufferRenderer?.commit()
            }
            MotionEvent.ACTION_CANCEL -> {
                frontBufferRenderer?.cancel()
            }
        }
        true
    }
}