package com.korolev.data.database

import android.content.Context
import com.korolev.data.database.model.BinInfoDB

class HistoryManager(context: Context) : HistoryRepository {
    private val daoHistory = HistoryDataBase.getInstance(context).getHistoryDao()
    var repository = HistoryRealization(daoHistory)

    override suspend fun allHistory(): List<BinInfoDB> = repository.allHistory()

    override suspend fun insertNote(binInfoDB: BinInfoDB) : Long {
        return repository.insertNote(binInfoDB)
    }

    override suspend fun deleteNote(binInfoDB: BinInfoDB) {
        repository.deleteNote(binInfoDB)
    }

    override suspend fun deleteAll() {
        repository.deleteAll()
    }

    override suspend fun getById(recordId: Long): BinInfoDB {
        return repository.getById(recordId)
    }
}