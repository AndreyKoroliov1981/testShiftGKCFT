package com.korolev.domain.home

import com.korolev.domain.home.model.BinInfo
import com.korolev.domain.home.model.Record

interface HomeInteractor {
    suspend fun getInfoForBin(bin: String): BinInfo
    suspend fun allHistory(): List<Record>
    suspend fun insertNote(record:Record): Long
    suspend fun deleteNote(record:Record)
    suspend fun deleteAll()
}