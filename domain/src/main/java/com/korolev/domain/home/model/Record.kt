package com.korolev.domain.home.model

data class Record(
    val id: Int = 0,
    val bin: String,
    val binInfo: BinInfo?
)