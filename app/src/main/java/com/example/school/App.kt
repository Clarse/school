package com.example.school

import android.app.Application
import com.tencent.mmkv.MMKV


class App : Application() {

    companion object {
        private var instance: App? = null
        fun getInstance(): App {
            return instance!!
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        MMKV.initialize(this)
    }

}