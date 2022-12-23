package com.korolev.data.network.api.home

import com.korolev.data.network.api.home.model.BinInfoResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface HomeRetrofitServices {
    @GET("/{bin}")
    fun getInfoForBin(
        @Path("bin") bin: String
    ): Call<BinInfoResponse>
}