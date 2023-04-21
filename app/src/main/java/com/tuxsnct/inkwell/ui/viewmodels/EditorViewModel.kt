package com.tuxsnct.inkwell.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuxsnct.inkwell.model.File
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor() : ViewModel() {
    val file: MutableLiveData<File> by lazy {
        MutableLiveData<File>()
    }

    fun updateFile(newFile: File) {
        println("updateFile: ${newFile.type}")
        file.value = newFile
    }
}