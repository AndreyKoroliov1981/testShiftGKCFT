package com.korolev.domain.home

import com.korolev.domain.home.model.BinInfo
import com.korolev.domain.home.model.Record

interface HomeRepository {
    suspend fun getInfoForBin(bin: String): BinInfo
    suspend fun allHistory(): List<Record>
    suspend fun insertNote(binInfo:Record): Long
    suspend fun deleteNote(binInfo:Record)
    suspend fun deleteAll()
}