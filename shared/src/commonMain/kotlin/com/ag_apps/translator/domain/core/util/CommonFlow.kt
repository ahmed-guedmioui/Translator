package com.ag_apps.translator.domain.core.util

import kotlinx.coroutines.flow.Flow

expect class CommonFlow<T>(flow: Flow<T>): Flow<T>

fun <T> Flow<T>.toCommonFlow(): CommonFlow<T> =
    CommonFlow(this)