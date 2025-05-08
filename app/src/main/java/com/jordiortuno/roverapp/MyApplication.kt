package com.jordiortuno.roverapp

import android.app.Application
import com.jordiortuno.rover.di.initKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }
}