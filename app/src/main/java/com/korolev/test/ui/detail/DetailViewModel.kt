package com.korolev.test.ui.detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.korolev.domain.detail.DetailInteractor
import com.korolev.test.common.BaseViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DetailViewModel @AssistedInject constructor(
    private val detailInteractor: DetailInteractor,
    @Assisted private val recordId: Long
) : BaseViewModel<DetailState>(DetailState()) {

    @AssistedFactory
    interface DetailViewModelFactory {
        fun create(recordId: Long): DetailViewModel
    }

    @Suppress("UNCHECKED_CAST")
    companion object {
        fun providesFactory(
            assistedFactory: DetailViewModelFactory,
            recordId: Long
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return assistedFactory.create(recordId) as T
            }
        }
    }

    init {
        launch {
            val record = detailInteractor.getById(recordId)
            updateState { copy (record = record) }
        }
    }

}