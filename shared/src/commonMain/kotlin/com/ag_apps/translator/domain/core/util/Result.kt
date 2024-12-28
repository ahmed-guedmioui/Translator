package com.ag_apps.translator.domain.core.util

sealed class Result<T>(val data: T?, val error: Throwable? = null) {
    class Success<T>(data: T) : Result<T>(data)
    class Error<T>(throwable: Throwable) : Result<T>(null, throwable)
}