package com.example.tenisapp

import android.app.Application
import com.example.tenisapp.data.AppContainer
import com.example.tenisapp.data.AppDataContainer

class TenisApplication : Application() {

    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
