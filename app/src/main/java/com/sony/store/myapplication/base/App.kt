package com.sony.store.myapplication.base

import android.app.Application
import com.sony.store.myapplication.utils.Base

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        Base().init(this)
    }
}