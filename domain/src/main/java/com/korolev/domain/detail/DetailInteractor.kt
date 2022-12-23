package com.korolev.domain.detail

import com.korolev.domain.home.model.Record

interface DetailInteractor {
    suspend fun getById(id: Long): Record
}