package com.korolev.test.ui.home

import androidx.lifecycle.viewModelScope
import com.korolev.domain.home.HomeInteractor
import com.korolev.domain.home.model.Record
import com.korolev.test.common.BaseViewModel
import com.korolev.test.common.IsHomeDataNetwork
import com.korolev.test.common.IsNotHomeDataNetwork
import kotlinx.coroutines.launch

class HomeViewModel(private val homeInteractor: HomeInteractor) :
    BaseViewModel<HomeState>(HomeState()) {

    init {
        getRecords()
    }

    private fun getRecords() {
        launch {
            val list = homeInteractor.allHistory()
            updateState { copy(records = list) }
        }
    }

    fun onClickDeleteRequest(item: Record) {
        launch {
            homeInteractor.deleteNote(item)
            getRecords()
        }
    }

    fun onClickSendRequest() {
        if (state.searchBin != "") {
            viewModelScope.launch {
                try {
                    val binInfo = homeInteractor.getInfoForBin(state.searchBin)
                    val id =
                        homeInteractor.insertNote(Record(bin = state.searchBin, binInfo = binInfo))
                    sideEffectSharedFlow.emit(IsHomeDataNetwork(data = id))

                } catch (e: Exception) {
                    sideEffectSharedFlow.emit(IsNotHomeDataNetwork(errorMessage = e.message.toString()))
                    homeInteractor.insertNote(Record(bin = state.searchBin, binInfo = null))
                    getRecords()
                }
            }
        }
    }

    fun onChangeSearchBin(newText: String) {
        updateState {
            copy(searchBin = newText)
        }
    }

    fun onClickDeleteAllList() {
        launch {
            homeInteractor.deleteAll()
            getRecords()
        }
    }
}
