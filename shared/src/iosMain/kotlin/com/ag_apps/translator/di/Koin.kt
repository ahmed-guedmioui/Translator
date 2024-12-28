package com.ag_apps.translator.di

import org.koin.mp.KoinPlatform
import com.ag_apps.translator.di.old.UserPresenter

fun getUserPresenter() : UserPresenter = KoinPlatform.getKoin().get()
