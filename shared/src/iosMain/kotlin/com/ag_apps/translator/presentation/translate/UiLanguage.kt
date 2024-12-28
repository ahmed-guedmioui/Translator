package com.ag_apps.translator.presentation.translate

import com.ag_apps.translator.domain.core.langauge.Language

actual class UiLanguage(
    val imageName: String,
    actual val language: Language
) {

    actual companion object {
        actual fun byCode(langCode: String): UiLanguage {
            return allLanguages.find { it.language.langCode == langCode }
                ?: throw IllegalArgumentException("Invalid or unsupported language code")
        }

        actual val allLanguages: List<UiLanguage>
            get() = Language.entries.map { language ->
                UiLanguage(
                    language = language,
                    imageName = language.langName.lowercase()
                )
            }
    }
}