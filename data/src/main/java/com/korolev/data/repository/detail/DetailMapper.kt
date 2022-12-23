package com.korolev.data.repository.detail

import com.korolev.data.database.model.BankInfoDB
import com.korolev.data.database.model.BinInfoDB
import com.korolev.data.database.model.CountryCardDB
import com.korolev.domain.home.model.BankInfo
import com.korolev.domain.home.model.BinInfo
import com.korolev.domain.home.model.CountryCard
import com.korolev.domain.home.model.Record

class DetailMapper {
    fun mapBinInfoFromDB(itemFromDB: BinInfoDB) = Record(
        id = itemFromDB.id,
        bin = itemFromDB.bin,
        binInfo = BinInfo(
            scheme = itemFromDB.binInfo?.scheme,
            type = itemFromDB.binInfo?.type,
            brand = itemFromDB.binInfo?.brand,
            country = mapCountryFromDB(itemFromDB.binInfo?.country),
            bank = mapBankFromDB(itemFromDB.binInfo?.bank)
        )
    )

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
}