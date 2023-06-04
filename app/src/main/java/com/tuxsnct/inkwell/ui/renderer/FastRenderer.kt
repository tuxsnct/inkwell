package com.tuxsnct.inkwell.ui.renderer

import android.annotation.SuppressLint
import android.graphics.Color
import android.opengl.EGL14
import android.opengl.GLES20
import android.opengl.Matrix
import android.view.MotionEvent
import android.view.SurfaceView
import android.view.View
import androidx.annotation.WorkerThread
import androidx.graphics.lowlatency.BufferInfo
import androidx.core.graphics.toColor
import androidx.graphics.lowlatency.GLFrontBufferedRenderer
import androidx.graphics.opengl.GLRenderer
import androidx.graphics.opengl.egl.EGLConfigAttributes
import androidx.graphics.opengl.egl.EGLManager
import androidx.graphics.opengl.egl.EGLSpec
// import androidx.input.motionprediction.MotionEventPredictor
import com.tuxsnct.inkwell.model.renderer.Segment
import com.tuxsnct.inkwell.ui.viewmodels.EditorViewModel


class FastRenderer(
    private var editorViewModel: EditorViewModel
) : GLFrontBufferedRenderer.Callback<Segment> {
    private val mvpMatrix = FloatArray(16)
    private val projection = FloatArray(16)

    private var glRenderer: GLRenderer? = null
    private var frontBufferRenderer: GLFrontBufferedRenderer<Segment>? = null
    // private var motionEventPredictor: MotionEventPredictor? = null

    private var lineRenderer: LineRenderer = LineRenderer()

    private var previousX: Float = 0f
    private var previousY: Float = 0f
    private var currentX: Float = 0f
    private var currentY: Float = 0f

    @WorkerThread // GLThread
    private fun obtainRenderer(): LineRenderer =
        if (lineRenderer.isInitialized) {
            lineRenderer
        } else {
            lineRenderer
                .apply {
                    initialize()
                }
        }

    override fun onDrawFrontBufferedLayer(
        eglManager: EGLManager,
        bufferInfo: BufferInfo,
        transform: FloatArray,
        param: Segment
    ) {
        val bufferWidth = bufferInfo.width
        val bufferHeight = bufferInfo.height
        GLES20.glViewport(0, 0, bufferWidth, bufferHeight)

        // Map Android coordinates to GL coordinates
        Matrix.orthoM(
            mvpMatrix,
            0,
            0f,
            bufferWidth.toFloat(),
            0f,
            bufferHeight.toFloat(),
            -1f,
            1f
        )
        Matrix.multiplyMM(projection, 0, mvpMatrix, 0, transform, 0)

        obtainRenderer().drawLine(projection, listOf(param), Color.BLACK.toColor())
    }

    override fun onDrawDoubleBufferedLayer(
        eglManager: EGLManager,
        bufferInfo: BufferInfo,
        transform: FloatArray,
        params: Collection<Segment>
    ) {
        val bufferWidth = bufferInfo.width
        val bufferHeight = bufferInfo.height
        GLES20.glViewport(0, 0, bufferWidth, bufferHeight)

        // Computes the ModelViewProjection Matrix
        Matrix.orthoM(
            mvpMatrix,
            0,
            0f,
            bufferWidth.toFloat(),
            0f,
            bufferHeight.toFloat(),
            -1f,
            1f
        )
        // perform matrix multiplication to transform the Android data to OpenGL reference
        Matrix.multiplyMM(projection, 0, mvpMatrix, 0, transform, 0)

        // Clear the screen with black
        GLES20.glClearColor(1.0f, 1.0f, 1.0f, 1.0f)
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT or GLES20.GL_DEPTH_BUFFER_BIT)

        editorViewModel.openGlLines.add(params.toList())

        // Render the entire scene (all lines)
        for (line in editorViewModel.openGlLines) {
            obtainRenderer().drawLine(projection, line, Color.BLACK.toColor())
        }
    }

    fun attachSurfaceView(surfaceView: SurfaceView) {
        glRenderer = GLRenderer(
            { EGLSpec.V14 },
            {
                loadConfig(configAttributes)
                ?: throw IllegalStateException("Unable to obtain config for 8 bit EGL configuration")
            }
        ).apply { start() }
        frontBufferRenderer = GLFrontBufferedRenderer(surfaceView, this, glRenderer)
        // motionEventPredictor = MotionEventPredictor.newInstance(surfaceView)
    }

    fun initialize() {
        frontBufferRenderer?.commit()
    }

    fun release() {
        frontBufferRenderer?.release(true)
        obtainRenderer().release()
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
                // Ask that the input system not batch MotionEvents
                // but instead deliver them as soon as they're available
                view.requestUnbufferedDispatch(event)

                currentX = event.x
                currentY = event.y

                // Create single point
                val segment = Segment(currentX, currentY, currentX, currentY)

                frontBufferRenderer?.renderFrontBufferedLayer(segment)

            }
            MotionEvent.ACTION_MOVE -> {
                previousX = currentX
                previousY = currentY
                currentX = event.x
                currentY = event.y

                val segment = Segment(previousX, previousY, currentX, currentY)

                // Send the short line to front buffered layer: fast rendering
                frontBufferRenderer?.renderFrontBufferedLayer(segment)

                /*
                val motionEventPredicted = motionEventPredictor?.predict()
                if(motionEventPredicted != null) {
                    val predictedSegment = Segment(currentX, currentY,
                        motionEventPredicted.x, motionEventPredicted.y)
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

    companion object {
        private val configAttributes: EGLConfigAttributes = EGLConfigAttributes {
            EGL14.EGL_LEVEL to 0
            EGL14.EGL_RENDERABLE_TYPE to EGL14.EGL_OPENGL_ES2_BIT
            EGL14.EGL_COLOR_BUFFER_TYPE to EGL14.EGL_RGB_BUFFER
            EGL14.EGL_RED_SIZE to 8
            EGL14.EGL_GREEN_SIZE to 8
            EGL14.EGL_BLUE_SIZE to 8
            EGL14.EGL_DEPTH_SIZE to 16
            EGL14.EGL_SAMPLE_BUFFERS to 1
            EGL14.EGL_SAMPLES to 4
        }
    }
}