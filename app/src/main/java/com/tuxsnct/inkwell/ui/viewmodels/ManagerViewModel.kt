package com.tuxsnct.inkwell.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.tuxsnct.inkwell.model.Folder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ManagerViewModel @Inject constructor() : ViewModel() {
    var isBottomSheetOpen by mutableStateOf(false)
        private set
    var skipPartiallyExpanded by mutableStateOf(false)
        private set

    fun openBottomSheet() {
        isBottomSheetOpen = true
    }

    fun closeBottomSheet() {
        isBottomSheetOpen = false
    }

    val folders = MutableStateFlow<List<Folder>>(emptyList())

    fun updateFolders(newFolders: List<Folder>) {
        folders.update { newFolders }
    }
}

