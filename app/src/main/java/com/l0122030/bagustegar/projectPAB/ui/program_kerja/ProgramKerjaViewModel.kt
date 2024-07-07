package com.l0122030.bagustegar.projectPAB.ui.program_kerja

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0122030.bagustegar.projectPAB.data.api.ApiConfigGlobal
import com.l0122030.bagustegar.projectPAB.data.model.program_kerja.ProgramKerja
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProgramKerjaViewModel : ViewModel() {

    private var _uiState: MutableLiveData<ProgramKerjaState> = MutableLiveData(ProgramKerjaState.Loading)
    val uiState: LiveData<ProgramKerjaState> get() = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiConfigGlobal.getApiService().getProgramKerja()
                val result = response.documents?.map {
                    ProgramKerja(
                        name = it.fields?.programkerjaname?.stringValue.orEmpty(),
                        jumlah2022 = it.fields?.jumlah2022?.integerValue?.toInt() ?: 0,
                        jumlah2023 = it.fields?.jumlah2023?.integerValue?.toInt() ?: 0,
                        jumlah2024 = it.fields?.jumlah2024?.integerValue?.toInt() ?: 0
                    )
                } ?: emptyList()

                if (result.isNotEmpty()) {
                    _uiState.postValue(ProgramKerjaState.Success(result))
                } else {
                    _uiState.postValue(ProgramKerjaState.Error(Throwable("Data not found")))
                }
            } catch (e: Exception) {
                _uiState.postValue(ProgramKerjaState.Error(e))
            }
        }
    }
}

sealed class ProgramKerjaState {
    object Loading : ProgramKerjaState()
    data class Success(val data: List<ProgramKerja>) : ProgramKerjaState()
    data class Error(val error: Throwable) : ProgramKerjaState()
}