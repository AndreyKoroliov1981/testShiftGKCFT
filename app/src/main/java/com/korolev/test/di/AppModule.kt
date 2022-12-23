package com.korolev.test.di

import android.content.Context
import com.korolev.domain.home.HomeInteractor
import com.korolev.test.ui.home.HomeViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class AppModule(val context: Context) {
    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    fun provideHomeViewModelFactory(homeInteractor: HomeInteractor): HomeViewModelFactory {
        return HomeViewModelFactory(homeInteractor = homeInteractor)
    }
}