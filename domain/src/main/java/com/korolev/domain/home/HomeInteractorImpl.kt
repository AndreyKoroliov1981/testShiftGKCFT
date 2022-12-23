package com.korolev.domain.home

import com.korolev.domain.home.model.BinInfo
import com.korolev.domain.home.model.Record

class HomeInteractorImpl(
    private val homeRepository: HomeRepository
) : HomeInteractor {
    override suspend fun getInfoForBin(bin: String): BinInfo {
        return homeRepository.getInfoForBin(bin)
    }

    override suspend fun allHistory(): List<Record> {
        return homeRepository.allHistory()
    }

    override suspend fun insertNote(record: Record): Long {
        return homeRepository.insertNote(record)
    }

    override suspend fun deleteNote(record: Record) {
        homeRepository.deleteNote(record)
    }

    override suspend fun deleteAll() {
        homeRepository.deleteAll()
    }
}