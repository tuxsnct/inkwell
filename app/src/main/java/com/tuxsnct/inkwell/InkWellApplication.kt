package com.tuxsnct.inkwell

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class InkWellApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}
