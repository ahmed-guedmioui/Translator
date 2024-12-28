package com.ag_apps.translator.domain.core.util

import kotlinx.coroutines.flow.MutableStateFlow

class IOSMutableStateFlow<T>(
    private val initialValue: T
): CommonMutableStateFlow<T>(MutableStateFlow(initialValue))