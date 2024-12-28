package com.ag_apps.translator.data.translate.local

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor

@Database(
    entities = [HistoryEntity::class],
    version = 1
)
@ConstructedBy(AppDatabaseConstructor::class)
abstract class HistoryDatabase: RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}

@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object AppDatabaseConstructor : RoomDatabaseConstructor<HistoryDatabase> {
    override fun initialize(): HistoryDatabase
}