package com.l0122030.bagustegar.projectPAB.ui.sumber_biaya

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0122030.bagustegar.projectPAB.data.api.ApiConfigGlobal
import com.l0122030.bagustegar.projectPAB.data.model.sumber_biaya.SumberBiaya
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SumberBiayaViewModel : ViewModel() {

    private var _uiState: MutableLiveData<SumberBiayaState> = MutableLiveData(SumberBiayaState.Loading)
    val uiState: LiveData<SumberBiayaState> get() = _uiState

    init {
        getSumberBiaya()
    }

    private fun getSumberBiaya() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiConfigGlobal.getApiService().getSumberBiaya()
                val result = response.documents?.map {
                    SumberBiaya(
                        name = it.fields?.biayaname?.stringValue.orEmpty(),
                        jumlah2022 = it.fields?.jumlah2022?.integerValue?.toInt() ?: 0,
                        jumlah2023 = it.fields?.jumlah2023?.integerValue?.toInt() ?: 0,
                        jumlah2024 = it.fields?.jumlah2024?.integerValue?.toInt() ?: 0
                    )
                } ?: emptyList()

                if (result.isNotEmpty()) {
                    _uiState.postValue(SumberBiayaState.Success(result))
                } else {
                    _uiState.postValue(SumberBiayaState.Error(Throwable("Data not found")))
                }
            } catch (e: Exception) {
                _uiState.postValue(SumberBiayaState.Error(e))
            }
        }
    }
}

sealed class SumberBiayaState {
    object Loading : SumberBiayaState()
    data class Success(val data: List<SumberBiaya>) : SumberBiayaState()
    data class Error(val error: Throwable) : SumberBiayaState()
}