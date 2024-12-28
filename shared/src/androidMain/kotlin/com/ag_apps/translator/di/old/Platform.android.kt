package com.ag_apps.translator.di.old

import android.os.Build
import com.ag_apps.translator.di.old.Platform

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
}

actual fun getPlatform(): Platform = AndroidPlatform()