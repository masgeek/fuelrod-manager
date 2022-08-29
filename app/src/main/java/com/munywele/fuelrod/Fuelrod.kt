package com.munywele.fuelrod

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate

class Fuelrod : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }
}