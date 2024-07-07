package com.l0122030.bagustegar.projectPAB.ui.koran

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0122030.bagustegar.projectPAB.data.api.ApiConfigGlobal
import com.l0122030.bagustegar.projectPAB.data.model.koran.Koran
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KoranViewModel : ViewModel() {

    private var _uiState: MutableLiveData<KoranState> = MutableLiveData(KoranState.Loading)
    val uiState: LiveData<KoranState> get() = _uiState

    private val monthOrder = listOf(
        "Januari", "Februari", "Maret", "April", "Mei", "Juni",
        "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    )

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiConfigGlobal.getApiService().getKoran()
                val result = response.documents?.map {
                    Koran(
                        name = it.fields?.koranname?.stringValue.orEmpty(),
                        jumlah2022 = it.fields?.jumlah2022?.integerValue?.toInt() ?: 0,
                        jumlah2023 = it.fields?.jumlah2023?.integerValue?.toInt() ?: 0,
                        jumlah2024 = it.fields?.jumlah2024?.integerValue?.toInt() ?: 0
                    )
                } ?: emptyList()

                // Sort the result by month order
                val sortedResult = result.sortedBy { monthOrder.indexOf(it.name) }

                if (sortedResult.isNotEmpty()) {
                    _uiState.postValue(KoranState.Success(sortedResult))
                } else {
                    _uiState.postValue(KoranState.Error(Throwable("Data not found")))
                }
            } catch (e: Exception) {
                _uiState.postValue(KoranState.Error(e))
            }
        }
    }
}

sealed class KoranState {
    object Loading : KoranState()
    data class Success(val data: List<Koran>) : KoranState()
    data class Error(val error: Throwable) : KoranState()
}
