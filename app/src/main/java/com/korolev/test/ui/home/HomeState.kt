package com.korolev.test.ui.home

import com.korolev.domain.home.model.Record

data class HomeState (
    val searchBin: String = "",
    val records: List<Record> = emptyList()
)