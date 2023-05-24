package com.tuxsnct.inkwell.ui.viewmodels

import android.view.MotionEvent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuxsnct.inkwell.model.file.Folder
import com.tuxsnct.inkwell.model.renderer.Segment
import com.tuxsnct.inkwell.ui.renderer.StylusState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor() : ViewModel() {
    val folder = MutableLiveData<Folder>()

    fun updateFile(newFolder: Folder) {
        folder.value = newFolder
    }

    private var _stylusState = MutableStateFlow(StylusState())
    val stylusState: StateFlow<StylusState> = _stylusState
    val openGlLines = mutableListOf<List<Segment>>()

    private fun requestRendering(stylusState: StylusState) {
        _stylusState.update {
            return@update stylusState
        }
    }

    private fun createStylusState(motionEvent: MotionEvent): StylusState {
        return StylusState(
            tilt = motionEvent.getAxisValue(MotionEvent.AXIS_TILT),
            pressure = motionEvent.pressure,
            orientation = motionEvent.orientation
        )
    }

    fun updateStylusVisualization(motionEvent: MotionEvent) {
        requestRendering(
            createStylusState(motionEvent)
        )
    }
}
