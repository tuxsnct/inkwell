package com.tuxsnct.inkwell.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuxsnct.inkwell.model.file.Folder
import com.tuxsnct.inkwell.model.renderer.Segment
import com.tuxsnct.inkwell.ui.renderer.ToolType
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor() : ViewModel() {
    private var folder: Folder? = null
    val folderName = MutableLiveData<String>()

    fun updateFile(newFolder: Folder) {
        folder = newFolder
        folderName.value = "${newFolder.type} ${newFolder.file.nameWithoutExtension}"
    }

    val canvasLines = mutableListOf<List<Segment>>()
    val toolType = MutableLiveData(ToolType.PEN)

    fun changeToolType(newToolType: ToolType) {
        toolType.value = newToolType
    }
}