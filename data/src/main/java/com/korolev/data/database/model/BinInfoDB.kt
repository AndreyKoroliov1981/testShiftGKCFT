package com.korolev.data.database.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "saved_request")
data class BinInfoDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val bin: String,
    @Embedded
    val binInfo: BinInfoForDB? = null
)

data class BinInfoForDB(
    val scheme: String? ,
    val type: String? ,
    val brand: String? ,
    @Embedded(prefix = "country")
    val country: CountryCardDB? ,
    @Embedded(prefix = "bank")
    val bank: BankInfoDB? ,
)

data class CountryCardDB(
    val name: String?,
    val currency: String?,
    val latitude: Double?,
    val longitude: Double?
)

data class BankInfoDB(
    val name: String?,
    val url: String?,
    val phone: String?,
    val city: String?
)