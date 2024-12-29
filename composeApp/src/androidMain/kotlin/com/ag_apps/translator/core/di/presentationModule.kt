package com.ag_apps.translator.core.di

import com.ag_apps.translator.translate.TranslateViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { TranslateViewModel() }
}