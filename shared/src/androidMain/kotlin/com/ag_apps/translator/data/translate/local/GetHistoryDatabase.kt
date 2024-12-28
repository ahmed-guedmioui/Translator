package com.ag_apps.translator.data.translate.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import kotlinx.coroutines.Dispatchers

fun getHistoryDatabase(context: Context): HistoryDatabase {
    val dbFile = context.getDatabasePath("history.db")
    return Room
        .databaseBuilder<HistoryDatabase>(
            context = context.applicationContext,
            name = dbFile.absolutePath
        )
        .setDriver(BundledSQLiteDriver())
        .setQueryCoroutineContext(Dispatchers.IO)
        .build()
}