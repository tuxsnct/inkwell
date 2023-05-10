package com.tuxsnct.inkwell.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuxsnct.inkwell.model.AppSpecificFile
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor() : ViewModel() {
    val file: MutableLiveData<AppSpecificFile> by lazy {
        MutableLiveData<AppSpecificFile>()
    }

    fun updateFile(newFile: AppSpecificFile) {
        file.value = newFile
    }
}