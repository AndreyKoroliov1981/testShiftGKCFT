package com.korolev.data.repository.home

import android.util.Log
import com.korolev.data.database.HistoryRepository
import com.korolev.data.database.model.BinInfoDB
import com.korolev.data.network.api.home.HomeRetrofitServices
import com.korolev.domain.home.HomeRepository
import com.korolev.domain.home.model.BinInfo
import com.korolev.domain.home.model.Record
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class HomeRepositoryImpl(
    private val homeMapper:  HomeMapper,
    private var homeRetrofitServices: HomeRetrofitServices,
    private var historyRepository: HistoryRepository
) : HomeRepository {
    override suspend fun getInfoForBin(bin: String): BinInfo =
        withContext(Dispatchers.IO) {
            val response = homeRetrofitServices.getInfoForBin(bin).execute().body()
            return@withContext homeMapper.mapBinInfoFromNetwork(response!!)
        }

    override suspend fun allHistory(): List<Record> {
        return homeMapper.mapBinInfoFromDB(historyRepository.allHistory())
    }

    override suspend fun insertNote(binInfo: Record): Long {
        return historyRepository.insertNote(homeMapper.mapBinInfoToDB(binInfo))
    }

    override suspend fun deleteNote(binInfo: Record) {
        historyRepository.deleteNote(homeMapper.mapBinInfoToDB(binInfo))
    }

    override suspend fun deleteAll() {
        historyRepository.deleteAll()
    }
}