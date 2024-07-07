package com.l0122030.bagustegar.projectPAB.ui.rekap

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0122030.bagustegar.projectPAB.data.api.ApiConfigGlobal
import com.l0122030.bagustegar.projectPAB.data.model.rekap.Rekap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RekapViewModel : ViewModel() {

    private var _uiState: MutableLiveData<RekapState> = MutableLiveData(RekapState.Loading)
    val uiState: LiveData<RekapState> get() = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiConfigGlobal.getApiService().getRekap()
                val result = response.documents?.map {
                    Rekap(
                        name = it.fields?.rekapname?.stringValue.orEmpty(),
                        jumlah2022 = it.fields?.jumlah2022?.integerValue?.toInt() ?: 0,
                        jumlah2023 = it.fields?.jumlah2023?.integerValue?.toInt() ?: 0,
                        jumlah2024 = it.fields?.jumlah2024?.integerValue?.toInt() ?: 0
                    )
                } ?: emptyList()

                if (result.isNotEmpty()) {
                    _uiState.postValue(RekapState.Success(result))
                } else {
                    _uiState.postValue(RekapState.Error(Throwable("Data not found")))
                }
            } catch (e: Exception) {
                _uiState.postValue(RekapState.Error(e))
            }
        }
    }
}

sealed class RekapState {
    object Loading : RekapState()
    data class Success(val data: List<Rekap>) : RekapState()
    data class Error(val error: Throwable) : RekapState()
}