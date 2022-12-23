package com.korolev.test.app

import android.app.Application
import com.korolev.test.di.AppComponent
import com.korolev.test.di.AppModule
import com.korolev.test.di.DaggerAppComponent

class App : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(context = this))
            .build()
    }
}
