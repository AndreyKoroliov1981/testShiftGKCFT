package com.korolev.data.repository.home

import com.korolev.data.database.model.BankInfoDB
import com.korolev.data.database.model.BinInfoDB
import com.korolev.data.database.model.BinInfoForDB
import com.korolev.data.database.model.CountryCardDB
import com.korolev.data.network.api.home.model.BankInfoResponse
import com.korolev.data.network.api.home.model.BinInfoResponse
import com.korolev.data.network.api.home.model.CountryCardResponse
import com.korolev.domain.home.model.BankInfo
import com.korolev.domain.home.model.BinInfo
import com.korolev.domain.home.model.CountryCard
import com.korolev.domain.home.model.Record

class HomeMapper {
    fun mapBinInfoFromNetwork(binInfoResponse: BinInfoResponse) =
        BinInfo(
            scheme = binInfoResponse.scheme,
            type = binInfoResponse.type,
            brand = binInfoResponse.brand,
            country = mapCountryFromNetwork(binInfoResponse.country),
            bank = mapBankFromNetwork(binInfoResponse.bank)
        )

    private fun mapCountryFromNetwork(countryCardResponse: CountryCardResponse?): CountryCard? {
        if (countryCardResponse != null)
            return CountryCard(
                name = countryCardResponse.name,
                currency = countryCardResponse.currency,
                latitude = countryCardResponse.latitude,
                longitude = countryCardResponse.longitude
            )
        return null
    }

    private fun mapBankFromNetwork(bankInfoResponse: BankInfoResponse?): BankInfo? {
        if (bankInfoResponse != null)
            return BankInfo(
                name = bankInfoResponse.name,
                url = bankInfoResponse.url,
                phone = bankInfoResponse.phone,
                city = bankInfoResponse.city
            )
        return null
    }

    fun mapBinInfoFromDB(listFromDB: List<BinInfoDB>): List<Record> {
        val newList = mutableListOf<Record>()
        for (i in listFromDB.indices) {
            val newItem =
            Record(
            id =  listFromDB[i].id,
            bin = listFromDB[i].bin,
            binInfo = BinInfo(
                scheme = listFromDB[i].binInfo?.scheme,
                type = listFromDB[i].binInfo?.type,
                brand = listFromDB[i].binInfo?.brand,
                country = mapCountryFromDB(listFromDB[i].binInfo?.country),
                bank = mapBankFromDB(listFromDB[i].binInfo?.bank)
            )
            )
            newList.add(newItem)
        }
        return newList
    }

    private fun mapCountryFromDB(countryCardDB: CountryCardDB?): CountryCard? {
        if (countryCardDB != null)
            return CountryCard(
                name = countryCardDB.name,
                currency = countryCardDB.currency,
                latitude = countryCardDB.latitude,
                longitude = countryCardDB.longitude
            )
        return null
    }

    private fun mapBankFromDB(bankInfoDB: BankInfoDB?): BankInfo? {
        if (bankInfoDB != null)
            return BankInfo(
                name = bankInfoDB.name,
                url = bankInfoDB.url,
                phone = bankInfoDB.phone,
                city = bankInfoDB.city
            )
        return null
    }

    fun mapBinInfoToDB(binInfo: Record) = BinInfoDB(
        id = binInfo.id,
        bin = binInfo.bin,
        binInfo = BinInfoForDB(
            scheme = binInfo.binInfo?.scheme,
            type = binInfo.binInfo?.type,
            brand = binInfo.binInfo?.brand,
            country = mapCountryToDB(binInfo.binInfo?.country),
            bank = mapBankToDB(binInfo.binInfo?.bank)
        )
    )

    private fun mapCountryToDB(countryCard: CountryCard?): CountryCardDB? {
        if (countryCard != null)
            return CountryCardDB(
                name = countryCard.name,
                currency = countryCard.currency,
                latitude = countryCard.latitude,
                longitude = countryCard.longitude
            )
        return null
    }

    private fun mapBankToDB(bankInfo: BankInfo?): BankInfoDB? {
        if (bankInfo != null)
            return BankInfoDB(
                name = bankInfo.name,
                url = bankInfo.url,
                phone = bankInfo.phone,
                city = bankInfo.city
            )
        return null
    }
}