package com.korolev.data.database

import com.korolev.data.database.model.BinInfoDB

interface HistoryRepository {
    suspend fun allHistory(): List<BinInfoDB>
    suspend fun insertNote(binInfoDB: BinInfoDB): Long
    suspend fun deleteNote(binInfoDB: BinInfoDB)
    suspend fun deleteAll()
    suspend fun getById(recordId: Long): BinInfoDB
}