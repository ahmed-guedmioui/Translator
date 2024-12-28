package com.ag_apps.translator.di

import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.ag_apps.translator.di.old.UserPresenter
import com.ag_apps.translator.di.old.data.UserRepository
import com.ag_apps.translator.di.old.data.UserRepositoryImpl

val appModule = module {
    singleOf(::UserRepositoryImpl) { bind<UserRepository>() }
    factoryOf(::UserPresenter)
}