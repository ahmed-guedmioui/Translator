package com.ag_apps.translator.di

import com.ag_apps.translator.data.translate.local.RoomHistoryDataSource
import com.ag_apps.translator.data.translate.remote.KtorTranslateClient
import com.ag_apps.translator.data.translate.remote.createHttpClient
import com.ag_apps.translator.domain.core.history.HistoryDataSource
import com.ag_apps.translator.domain.translate.TranslateClient
import com.ag_apps.translator.domain.translate.TranslateUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

expect val dataPlatformModule: Module

val dataSharedModule = module {

    single { createHttpClient() }

    single<TranslateClient> { KtorTranslateClient(get()) }

    single<HistoryDataSource> { RoomHistoryDataSource(get()) }

    single { TranslateUseCase(get(), get()) }

}