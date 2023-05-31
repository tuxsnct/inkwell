package com.tuxsnct.inkwell.ui.renderer

import android.graphics.Color
import android.opengl.GLES31
import com.tuxsnct.inkwell.model.renderer.Segment
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer

class LineRenderer {

    var isInitialized = false

    private var vertexShader: Int = -1

    private var fragmentShader: Int = -1

    private var glProgram: Int = -1

    private var positionHandle: Int = -1

    private var mvpMatrixHandle: Int = -1

    private var colorHandle: Int = -1

    // private val colorArray = FloatArray(4)

    private var vertexBuffer: FloatBuffer? = null

    private val lineCoords = FloatArray(LINE_COORDS_SIZE)


    fun initialize() {
        release()
        vertexShader = loadShader(GLES31.GL_VERTEX_SHADER, VERTEX_SHADER_CODE)
        fragmentShader = loadShader(GLES31.GL_FRAGMENT_SHADER, FRAGMENT_SHADER_CODE)
        glProgram = GLES31.glCreateProgram()
        GLES31.glAttachShader(glProgram, vertexShader)
        GLES31.glAttachShader(glProgram, fragmentShader)
        GLES31.glLinkProgram(glProgram)
        val bb: ByteBuffer =
            ByteBuffer.allocateDirect( // (number of coordinate values * 4 bytes per float)
                LINE_COORDS_SIZE * FLOAT_BYTE_SIZE
            )
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder())
        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer().apply {
            put(lineCoords)
            position(0)
        }
        positionHandle = GLES31.glGetAttribLocation(glProgram, V_POSITION)
        mvpMatrixHandle = GLES31.glGetUniformLocation(glProgram, U_MVP_MATRIX)
        colorHandle = GLES31.glGetUniformLocation(glProgram, V_COLOR)

        isInitialized = true
    }

    fun release() {
        if (vertexShader != -1) {
            GLES31.glDeleteShader(vertexShader)
            vertexShader = -1
        }
        if (fragmentShader != -1) {
            GLES31.glDeleteShader(fragmentShader)
            fragmentShader = -1
        }
        if (glProgram != -1) {
            GLES31.glDeleteProgram(glProgram)
            glProgram = -1
        }
    }

    fun drawLine(
        mvpMatrix: FloatArray,
        line: Collection<Segment>,
        color: Color,
    ) {
        GLES31.glUseProgram(glProgram)
        GLES31.glEnable(GLES31.GL_BLEND)
        GLES31.glBlendFunc(GLES31.GL_SRC_ALPHA, GLES31.GL_ONE_MINUS_SRC_ALPHA)
        GLES31.glLineWidth(1.0f)
        GLES31.glEnableVertexAttribArray(positionHandle)

        val colorArray = FloatArray(4)
        // Convert Android color to GL Color
        colorArray[0] = color.red()
        colorArray[1] = color.green()
        colorArray[2] = color.blue()
        colorArray[3] = color.alpha()

        // Set color for drawing the line
        GLES31.glUniform4fv(colorHandle, 1, colorArray, 0)
        GLES31.glUniformMatrix4fv(mvpMatrixHandle, 1, false, mvpMatrix, 0)
        vertexBuffer?.let { buffer ->
            for (vertex in line) {
                lineCoords[0] = vertex.x1
                lineCoords[1] = vertex.y1
                lineCoords[2] = 0f // Z1 since we are in 2D set to 0f
                lineCoords[3] = vertex.x2
                lineCoords[4] = vertex.y2
                lineCoords[5] = 0f // Z2 since we are in 2D set to 0f
                buffer.put(lineCoords)
                buffer.position(0)

                // Prepare the triangle coordinate data
                GLES31.glVertexAttribPointer(
                    positionHandle, COORDS_PER_VERTEX,
                    GLES31.GL_FLOAT, false,
                    VERTEX_STRIDE, buffer
                )
                // Render
                GLES31.glDrawArrays(GLES31.GL_LINES, 0, VERTEX_COUNT)
            }
        }
        GLES31.glDisableVertexAttribArray(positionHandle)
    }

    companion object {
        const val COORDS_PER_VERTEX = 3
        const val LINE_COORDS_SIZE = 6
        const val FLOAT_BYTE_SIZE = 4

        private const val VERTEX_COUNT: Int = LINE_COORDS_SIZE / COORDS_PER_VERTEX
        private const val VERTEX_STRIDE: Int = COORDS_PER_VERTEX * FLOAT_BYTE_SIZE // 4 bytes per vertex
        private const val U_MVP_MATRIX = "uMVPMatrix"
        private const val V_POSITION = "vPosition"
        private const val VERTEX_SHADER_CODE =
            """
            uniform mat4 $U_MVP_MATRIX;
            attribute vec4 $V_POSITION;
            void main() { // the matrix must be included as a modifier of gl_Position
              gl_Position = $U_MVP_MATRIX * $V_POSITION;
            }
            """
        private const val V_COLOR = "vColor"
        private const val FRAGMENT_SHADER_CODE =
            """
            precision mediump float;
            uniform vec4 $V_COLOR;
            void main() {
              gl_FragColor = $V_COLOR;
            }                
            """

        fun loadShader(type: Int, shaderCode: String?): Int {
            val shader = GLES31.glCreateShader(type)
            GLES31.glShaderSource(shader, shaderCode)
            GLES31.glCompileShader(shader)
            return shader
        }
    }
}