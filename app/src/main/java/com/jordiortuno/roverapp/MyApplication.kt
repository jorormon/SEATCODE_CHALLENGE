package com.jordiortuno.roverapp

import android.app.Application
import com.jordiortuno.rover.di.initKoin
import org.koin.android.ext.koin.androidContext

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin() {
            androidContext(this@MyApplication)
        }
    }
}