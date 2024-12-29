package com.ag_apps.translator

import android.app.Application
import com.ag_apps.translator.core.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidContext(this@Application)
            androidLogger(Level.DEBUG)
            modules(presentationModule)
        }
    }
}