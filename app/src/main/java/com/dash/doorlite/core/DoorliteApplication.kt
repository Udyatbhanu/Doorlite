package com.dash.doorlite.core

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DoorliteApplication : Application(){
    override fun onCreate(){
        super.onCreate()
    }
}