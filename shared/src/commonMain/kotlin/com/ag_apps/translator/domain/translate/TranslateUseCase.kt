package com.ag_apps.translator.domain.translate

import com.ag_apps.translator.domain.core.history.HistoryDataSource
import com.ag_apps.translator.domain.core.history.HistoryItem
import com.ag_apps.translator.domain.core.langauge.Language
import com.ag_apps.translator.domain.core.util.Result

class TranslateUseCase(
    private val client: TranslateClient,
    private val historyDatasource: HistoryDataSource,
) {

    suspend operator fun invoke(
        fromLanguage: Language,
        fromText: String,
        toLanguage: Language
    ): Result<String> {
        try {
            val translatedText = client.translate(
                fromLanguage, fromText, toLanguage
            )

            historyDatasource.insertHistoryItem(
                HistoryItem(
                    id = null,
                    fromLanguageCode = fromLanguage.langCode,
                    fromText = fromText,
                    toLanguageCode = toLanguage.langCode,
                    toText = translatedText,
                )
            )

            return Result.Success(translatedText)

        } catch (e: TranslateException) {
            e.printStackTrace()
            return Result.Error(e)
        }
    }

}