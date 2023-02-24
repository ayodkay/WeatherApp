package com.example.weatherapp

import android.app.Application
import com.example.weatherapp.di.NetworkModule
import com.example.weatherapp.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MainApplication)
            // Load modules
            modules(appModules, NetworkModule().weatherServiceModule())
        }
    }
}