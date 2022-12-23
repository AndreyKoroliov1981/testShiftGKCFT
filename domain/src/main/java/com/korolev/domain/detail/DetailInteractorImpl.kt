package com.korolev.domain.detail

import com.korolev.domain.home.model.Record

class DetailInteractorImpl(
    private val detailRepository: DetailRepository
) : DetailInteractor {
    override suspend fun getById(id: Long): Record {
        return detailRepository.getById(id)
    }
}