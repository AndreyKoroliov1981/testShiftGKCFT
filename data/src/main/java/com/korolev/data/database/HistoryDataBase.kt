package com.korolev.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.korolev.data.database.model.BinInfoDB

@Database(entities = [BinInfoDB::class], version = 1)
abstract class HistoryDataBase : RoomDatabase() {
    abstract fun getHistoryDao(): BinInfoDao

    companion object {
        private var database: HistoryDataBase? = null

        @Synchronized
        fun getInstance(context: Context): HistoryDataBase {
            return if (database == null) {
                database = Room.databaseBuilder(context, HistoryDataBase::class.java, "db").build()
                database as HistoryDataBase
            } else {
                database as HistoryDataBase
            }
        }
    }
}