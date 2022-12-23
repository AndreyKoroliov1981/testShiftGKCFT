package com.korolev.domain.home.model

data class BinInfo(
    val scheme: String? ,
    val type: String? ,
    val brand: String? ,
    val country: CountryCard? ,
    val bank: BankInfo? ,
)

data class CountryCard(
    val name: String?,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?
)

data class BankInfo(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)