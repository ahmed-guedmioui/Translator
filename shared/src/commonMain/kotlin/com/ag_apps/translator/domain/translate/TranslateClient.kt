package com.ag_apps.translator.domain.translate

import com.ag_apps.translator.domain.core.langauge.Language

interface TranslateClient {
    suspend fun translate(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): String
}