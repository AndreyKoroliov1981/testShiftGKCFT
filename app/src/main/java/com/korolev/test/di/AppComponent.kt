package com.korolev.test.di

import com.korolev.test.ui.detail.DetailFragment
import com.korolev.test.ui.home.HomeFragment
import dagger.Component

@Component(modules = [AppModule::class,DomainModule::class,DataModule::class])
interface AppComponent {
    fun injectHomeFragment(homeFragment: HomeFragment)
    fun injectDetailFragment(detailFragment: DetailFragment)
}