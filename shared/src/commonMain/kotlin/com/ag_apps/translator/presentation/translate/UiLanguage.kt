package com.ag_apps.translator.presentation.translate

import com.ag_apps.translator.domain.core.langauge.Language

expect class UiLanguage {
    val language: Language
    companion object {
        fun byCode(langCode: String): UiLanguage
        val allLanguages: List<UiLanguage>
    }
}