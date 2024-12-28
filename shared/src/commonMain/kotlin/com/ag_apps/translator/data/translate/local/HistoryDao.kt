package com.ag_apps.translator.data.translate.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Upsert
    suspend fun upsert(historyEntity: HistoryEntity)

    @Delete
    suspend fun delete(historyEntity: HistoryEntity)

    @Query("SELECT * FROM historyentity ORDER BY timestamp DESC")
    fun getHistory(): Flow<List<HistoryEntity>>

}