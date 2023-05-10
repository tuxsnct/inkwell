package com.tuxsnct.inkwell.data

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class PreferencesRepository @Inject constructor(
    @ApplicationContext context: Context
) {
    private val preferences: SharedPreferences = context.getSharedPreferences("inkwell", Context.MODE_PRIVATE)
    private lateinit var listener: SharedPreferences.OnSharedPreferenceChangeListener

    fun setPreference(key: String, value: String) {
        preferences.edit().putString(key, value).apply()
    }

    fun getPreference(key: String): String? {
        return preferences.getString(key, null)
    }

    fun getListener(): SharedPreferences.OnSharedPreferenceChangeListener {
        listener = SharedPreferences.OnSharedPreferenceChangeListener { _, _ ->
            // do something
        }
        return listener
    }
}