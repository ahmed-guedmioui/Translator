package com.ag_apps.translator.data.core.di

import com.ag_apps.translator.data.translate.local.HistoryDatabase
import com.ag_apps.translator.data.translate.local.getHistoryDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

actual val dataPlatformModule = module {
    single<HistoryDatabase> { getHistoryDatabase(androidApplication()) }
}