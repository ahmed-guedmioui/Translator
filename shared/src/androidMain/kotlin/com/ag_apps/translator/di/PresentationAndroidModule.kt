package com.ag_apps.translator.di

import com.ag_apps.translator.presentation.translate.TranslateViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val presentationPlatformModule = module {
    viewModel { TranslateViewModel() }
}