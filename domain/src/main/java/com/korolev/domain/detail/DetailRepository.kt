package com.korolev.domain.detail

import com.korolev.domain.home.model.Record

interface DetailRepository {
    suspend fun getById(id:Long):Record
}