package com.ag_apps.translator.di.old

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform