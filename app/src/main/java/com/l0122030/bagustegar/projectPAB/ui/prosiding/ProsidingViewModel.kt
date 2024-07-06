package com.l0122030.bagustegar.projectPAB.ui.prosiding

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0122030.bagustegar.projectPAB.data.api.ApiConfigGlobal
import com.l0122030.bagustegar.projectPAB.data.model.prosiding.Prosiding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProsidingViewModel : ViewModel() {

    private var _uiState: MutableLiveData<ProsidingState> = MutableLiveData(ProsidingState.Loading)
    val uiState: LiveData<ProsidingState> get() = _uiState

    init {
        getProsiding()
    }

    private fun getProsiding() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiConfigGlobal.getApiService().getProsiding()
                val result = response.documents?.map {
                    Prosiding(
                        name = it.fields?.prosidingname?.stringValue.orEmpty(),
                        jumlah2022 = it.fields?.jumlah2022?.integerValue?.toInt() ?: 0,
                        jumlah2023 = it.fields?.jumlah2023?.integerValue?.toInt() ?: 0,
                        jumlah2024 = it.fields?.jumlah2024?.integerValue?.toInt() ?: 0
                    )
                } ?: emptyList()

                if (result.isNotEmpty()) {
                    _uiState.postValue(ProsidingState.Success(result))
                } else {
                    _uiState.postValue(ProsidingState.Error(Throwable("Data not found")))
                }
            } catch (e: Exception) {
                _uiState.postValue(ProsidingState.Error(e))
            }
        }
    }
}

sealed class ProsidingState {
    object Loading : ProsidingState()
    data class Success(val data: List<Prosiding>) : ProsidingState()
    data class Error(val error: Throwable) : ProsidingState()
}