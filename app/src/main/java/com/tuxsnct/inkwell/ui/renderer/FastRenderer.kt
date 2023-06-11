package com.tuxsnct.inkwell.ui.renderer

import android.annotation.SuppressLint
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import androidx.graphics.lowlatency.CanvasFrontBufferedRenderer
// import androidx.input.motionprediction.MotionEventPredictor
import com.tuxsnct.inkwell.model.renderer.Segment
import com.tuxsnct.inkwell.ui.viewmodels.EditorViewModel


class FastRenderer(
    private var editorViewModel: EditorViewModel
) : CanvasFrontBufferedRenderer.Callback<Segment> {
    private var paint: Paint? = null

    private var frontBufferRenderer: CanvasFrontBufferedRenderer<Segment>? = null
    // private var motionEventPredictor: MotionEventPredictor? = null

    private var previousX: Float = 0f
    private var previousY: Float = 0f
    private var currentX: Float = 0f
    private var currentY: Float = 0f

    override fun onDrawFrontBufferedLayer(
        canvas: Canvas,
        bufferWidth: Int,
        bufferHeight: Int,
        param: Segment
    ) {
        paint?.let {
            it.style = Paint.Style.STROKE
            it.strokeWidth = 2.0f + 8.0f * (param.pressure ?: 1.0f)
            it.strokeCap = Paint.Cap.ROUND
            it.strokeJoin = Paint.Join.ROUND
            canvas.drawLine(param.x1, param.y1, param.x2, param.y2, it)
        }
    }

    override fun onDrawMultiBufferedLayer(
        canvas: Canvas,
        bufferWidth: Int,
        bufferHeight: Int,
        params: Collection<Segment>
    ) {
        canvas.drawColor(Color.WHITE)

        editorViewModel.canvasLines.add(params.toList())

        paint?.let {
            it.style = Paint.Style.STROKE
            it.strokeCap = Paint.Cap.ROUND
            it.strokeJoin = Paint.Join.ROUND

            for (line in editorViewModel.canvasLines) {
                for (segment in line) {
                    it.strokeWidth = 2.0f + 8.0f * (segment.pressure ?: 1.0f)
                    canvas.drawLine(segment.x1, segment.y1, segment.x2, segment.y2, it)
                }
            }
        }
    }

    fun attachSurfaceView(surfaceView: SurfaceView) {
        paint = Paint()
        frontBufferRenderer = CanvasFrontBufferedRenderer(surfaceView, this)
        // motionEventPredictor = MotionEventPredictor.newInstance(surfaceView)
    }

    fun initialize() {
        frontBufferRenderer?.commit()
    }

    fun release() {
        frontBufferRenderer?.release(true)
    }

    fun onWindowFocusChanged(hasWindowFocus: Boolean) {
        if (hasWindowFocus) frontBufferRenderer?.commit()
    }

    @SuppressLint("ClickableViewAccessibility")
    val onTouchListener = View.OnTouchListener { view, event ->
        editorViewModel.updateStylusVisualization(event)
        // motionEventPredictor?.record(event)

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                view.requestUnbufferedDispatch(event)

                currentX = event.x
                currentY = event.y

                val segment = Segment(currentX, currentY, currentX, currentY, event.pressure,
                    event.getAxisValue(MotionEvent.AXIS_TILT), event.getAxisValue(MotionEvent.AXIS_ORIENTATION))

                frontBufferRenderer?.renderFrontBufferedLayer(segment)

            }
            MotionEvent.ACTION_MOVE -> {
                previousX = currentX
                previousY = currentY
                currentX = event.x
                currentY = event.y

                val segment = Segment(previousX, previousY, currentX, currentY, event.pressure,
                    event.getAxisValue(MotionEvent.AXIS_TILT), event.getAxisValue(MotionEvent.AXIS_ORIENTATION))

                frontBufferRenderer?.renderFrontBufferedLayer(segment)

                /*
                val motionEventPredicted = motionEventPredictor?.predict()
                if(motionEventPredicted != null) {
                    val predictedSegment = Segment(currentX, currentY,
                        motionEventPredicted.x, motionEventPredicted.y, isPredicted = true)
                    frontBufferRenderer?.renderFrontBufferedLayer(predictedSegment)
                }
                 */
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