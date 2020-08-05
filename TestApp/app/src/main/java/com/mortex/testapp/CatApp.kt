package com.mortex.testapp

import android.app.Application
import com.mortex.testapp.di.catModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CatApp: Application() {


    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CatApp)
            modules(catModule)
        }
    }
}