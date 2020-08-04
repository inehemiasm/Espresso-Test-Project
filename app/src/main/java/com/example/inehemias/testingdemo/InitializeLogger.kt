package com.example.inehemias.testingdemo

import android.app.Application
import timber.log.Timber

class InitializeLogger : Application() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
