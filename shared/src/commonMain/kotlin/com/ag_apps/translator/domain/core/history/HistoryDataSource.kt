package com.ag_apps.translator.domain.core.history

import com.ag_apps.translator.domain.core.util.CommonFlow

interface HistoryDataSource {
    fun getHistory(): CommonFlow<List<HistoryItem>>
    fun test(): String
    suspend fun insertHistoryItem(item: HistoryItem)
}