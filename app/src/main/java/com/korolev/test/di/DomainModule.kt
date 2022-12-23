package com.korolev.test.di

import com.korolev.domain.detail.DetailInteractor
import com.korolev.domain.detail.DetailInteractorImpl
import com.korolev.domain.detail.DetailRepository
import com.korolev.domain.home.HomeInteractor
import com.korolev.domain.home.HomeInteractorImpl
import com.korolev.domain.home.HomeRepository
import dagger.Module
import dagger.Provides

@Module
class DomainModule {
    @Provides
    fun provideHomeInteractor(homeRepository: HomeRepository): HomeInteractor {
        return HomeInteractorImpl(homeRepository = homeRepository)
    }
    @Provides
    fun provideDetailInteractor(detailRepository: DetailRepository): DetailInteractor {
        return DetailInteractorImpl(detailRepository = detailRepository)
    }
}