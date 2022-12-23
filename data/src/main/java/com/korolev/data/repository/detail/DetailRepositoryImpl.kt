package com.korolev.data.repository.detail

import com.korolev.data.database.HistoryRepository
import com.korolev.domain.detail.DetailRepository
import com.korolev.domain.home.model.Record

class DetailRepositoryImpl(
    private val detailMapper: DetailMapper,
    private var historyRepository: HistoryRepository
) : DetailRepository {
    override suspend fun getById(id: Long): Record {
        return detailMapper.mapBinInfoFromDB(historyRepository.getById(id))
    }
}