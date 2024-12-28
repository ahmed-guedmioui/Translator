package com.ag_apps.translator.data.translate.local

import com.ag_apps.translator.domain.core.history.HistoryDataSource
import com.ag_apps.translator.domain.core.history.HistoryItem
import com.ag_apps.translator.domain.core.util.CommonFlow
import com.ag_apps.translator.domain.core.util.toCommonFlow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.Clock

class RoomHistoryDataSource(
    db: HistoryDatabase
) : HistoryDataSource {

    private val dao = db.historyDao()

    override fun getHistory(): CommonFlow<List<HistoryItem>> {
        return dao
            .getHistory()
            .map { it.map { historyEntity -> historyEntity.toHistoryItem() } }
            .toCommonFlow()
    }

    override fun test(): String {
        return "AHMEDDDDDDXDD"
    }


    override suspend fun insertHistoryItem(item: HistoryItem) {
        dao.upsert(
            HistoryEntity(
                fromLanguageCode = item.fromLanguageCode,
                fromText = item.fromText,
                toLanguageCode = item.toLanguageCode,
                toText = item.toText,
                timestamp = Clock.System.now().toEpochMilliseconds()
            )
        )
    }
}