package com.tuxsnct.inkwell.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManagerViewModel @Inject constructor() : ViewModel() {
     val selectedTab: MutableLiveData<Int> by lazy {
        MutableLiveData<Int>()
    }

    fun updateSelectedTab(newSelectedTab: Int) {
        selectedTab.value = newSelectedTab
    }
}

