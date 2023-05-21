package com.tuxsnct.inkwell.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tuxsnct.inkwell.model.Folder
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EditorViewModel @Inject constructor() : ViewModel() {
    val folder: MutableLiveData<Folder> by lazy {
        MutableLiveData<Folder>()
    }

    fun updateFile(newFolder: Folder) {
        folder.value = newFolder
    }
}