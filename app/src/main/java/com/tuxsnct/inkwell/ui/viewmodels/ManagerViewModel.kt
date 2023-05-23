package com.tuxsnct.inkwell.ui.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
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
}

