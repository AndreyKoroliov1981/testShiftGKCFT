package com.korolev.data.network.api.home.model

data class BinInfoResponse(
    val number: NumberCardResponse?,
    val scheme: String?,
    val type: String?,
    val brand: String?,
    val prepaid: Boolean?,
    val country: CountryCardResponse?,
    val bank: BankInfoResponse?,
)

data class NumberCardResponse(
    val length: Int?,
    val luhn: Boolean?
)

data class CountryCardResponse(
    val numeric: String?,
    val alpha2: String?,
    val name: String?,
    val emoji: String?,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?
)

data class BankInfoResponse(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)