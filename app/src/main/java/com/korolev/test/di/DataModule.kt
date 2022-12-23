package com.korolev.test.di

import android.content.Context
import com.korolev.data.database.HistoryManager
import com.korolev.data.database.HistoryRepository
import com.korolev.data.network.api.home.HomeCommon
import com.korolev.data.network.api.home.HomeRetrofitServices
import com.korolev.data.repository.detail.DetailMapper
import com.korolev.data.repository.detail.DetailRepositoryImpl
import com.korolev.data.repository.home.HomeMapper
import com.korolev.data.repository.home.HomeRepositoryImpl
import com.korolev.domain.detail.DetailRepository
import com.korolev.domain.home.HomeRepository
import dagger.Module
import dagger.Provides

@Module
class DataModule {

    @Provides
    fun provideHomeRepository(
        homeMapper: HomeMapper,
        homeRetrofitServices: HomeRetrofitServices,
        historyRepository: HistoryRepository
    ): HomeRepository {
        return HomeRepositoryImpl(
            homeMapper = homeMapper,
            homeRetrofitServices = homeRetrofitServices,
            historyRepository = historyRepository
        )
    }

    @Provides
    fun provideDetailRepository(
        detailMapper: DetailMapper,
        historyRepository: HistoryRepository
    ): DetailRepository {
        return DetailRepositoryImpl(
            detailMapper = detailMapper,
            historyRepository = historyRepository
        )
    }

    @Provides
    fun provideHomeMapper(): HomeMapper {
        return HomeMapper()
    }
    @Provides
    fun provideDetailMapper(): DetailMapper {
        return DetailMapper()
    }

    @Provides
    fun provideHomeRetrofitServices(): HomeRetrofitServices {
        return HomeCommon.homeRetrofitService
    }
    @Provides
    fun provideHistoryRepository(context: Context): HistoryRepository {
        return HistoryManager(context = context)
    }
}