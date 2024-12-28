package com.ag_apps.translator.data.translate.local

import com.ag_apps.translator.domain.core.history.HistoryItem

fun HistoryEntity.toHistoryItem(): HistoryItem {
    return HistoryItem(
        id = id.toLong(),
        fromLanguageCode = fromLanguageCode,
        fromText = fromText,
        toLanguageCode = toLanguageCode,
        toText = toText
    )
}