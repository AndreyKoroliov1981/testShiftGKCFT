package com.korolev.data.network.api.home

import com.korolev.data.BuildConfig

object HomeCommon {
    val homeRetrofitService: HomeRetrofitServices
        get() = HomeRetrofitClient.getClient(BuildConfig.API_ENDPOINT)
            .create(HomeRetrofitServices::class.java)
}