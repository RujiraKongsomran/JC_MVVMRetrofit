package com.rujirakongsomran.jc_mvvmretrofit

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WeatherApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}