package com.l0122030.bagustegar.projectPAB.ui.haki

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0122030.bagustegar.projectPAB.data.api.ApiConfigGlobal
import com.l0122030.bagustegar.projectPAB.data.model.haki.Haki
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HakiViewModel : ViewModel() {

    private var _uiState: MutableLiveData<HakiState> = MutableLiveData(HakiState.Loading)
    val uiState: LiveData<HakiState> get() = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiConfigGlobal.getApiService().getHaki()
                val result = response.documents?.map {
                    Haki(
                        name = it.fields?.hakiname?.stringValue.orEmpty(),
                        jumlah2022 = it.fields?.jumlah2022?.integerValue?.toInt() ?: 0,
                        jumlah2023 = it.fields?.jumlah2023?.integerValue?.toInt() ?: 0,
                        jumlah2024 = it.fields?.jumlah2024?.integerValue?.toInt() ?: 0
                    )
                } ?: emptyList()

                if (result.isNotEmpty()) {
                    _uiState.postValue(HakiState.Success(result))
                } else {
                    _uiState.postValue(HakiState.Error(Throwable("Data not found")))
                }
            } catch (e: Exception) {
                _uiState.postValue(HakiState.Error(e))
            }
        }
    }
}

sealed class HakiState {
    object Loading : HakiState()
    data class Success(val data: List<Haki>) : HakiState()
    data class Error(val error: Throwable) : HakiState()
}