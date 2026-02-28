package com.org.lab.ui.description_screen

import android.content.Context
import androidx.core.content.edit

class PreferenceManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("app_settings", Context.MODE_PRIVATE)

    fun isOnboardingCompleted(): Boolean {
        return sharedPreferences.getBoolean("completed_onboarding", false)
    }

    fun setOnboardingCompleted() {
        sharedPreferences.edit { putBoolean("completed_onboarding", true) }
    }
}