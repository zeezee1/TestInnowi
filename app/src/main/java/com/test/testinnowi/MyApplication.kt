package com.test.testinnowi

import android.app.Application

class MyApplication : Application() {

    companion object {
        private var mInstance: MyApplication? = null
        fun getInstance(): MyApplication {
            return mInstance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        mInstance = this
    }
}