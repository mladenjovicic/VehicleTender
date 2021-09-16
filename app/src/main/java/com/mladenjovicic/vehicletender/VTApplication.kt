package com.mladenjovicic.vehicletender

import android.app.Application

class VTApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: VTApplication
    }
}