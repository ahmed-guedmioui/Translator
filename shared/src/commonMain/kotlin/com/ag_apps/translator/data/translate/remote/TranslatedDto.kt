package com.ag_apps.translator.data.translate.remote

import kotlinx.serialization.Serializable

@Serializable
data class TranslatedDto(
    val translatedText: String
)
