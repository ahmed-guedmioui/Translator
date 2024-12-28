package com.ag_apps.translator.domain.core.util

import kotlinx.coroutines.flow.MutableStateFlow

expect class CommonMutableStateFlow<T>(flow: MutableStateFlow<T>): MutableStateFlow<T>

fun <T> MutableStateFlow<T>.toCommonMutableStateFlow(): CommonMutableStateFlow<T> = CommonMutableStateFlow(this)