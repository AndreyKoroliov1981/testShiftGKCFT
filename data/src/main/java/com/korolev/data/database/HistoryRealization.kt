package com.korolev.data.database

import com.korolev.data.database.model.BinInfoDB

class HistoryRealization(private val historyDao: BinInfoDao) {

    suspend fun allHistory(): List<BinInfoDB> {
        return historyDao.getAllHistory()
    }

    suspend fun insertNote(historyModel: BinInfoDB): Long {
        return historyDao.insert(historyModel)
    }

    suspend fun deleteNote(historyModel: BinInfoDB) {
        historyDao.delete(historyModel)
    }

    suspend fun deleteAll() {
        historyDao.deleteAllHistory()
    }

    suspend fun getById(recordId: Long): BinInfoDB {
        return historyDao.getById(recordId)
    }
}