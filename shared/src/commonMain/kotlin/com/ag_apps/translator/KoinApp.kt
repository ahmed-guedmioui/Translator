package com.ag_apps.translator

import com.ag_apps.translator.data.core.di.dataPlatformModule
import com.ag_apps.translator.data.core.di.dataSharedModule
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes

fun initKoin(config: KoinAppDeclaration? = null) {
    startKoin {
        includes(config)
        modules(
            dataSharedModule,
            dataPlatformModule
        )
    }
}