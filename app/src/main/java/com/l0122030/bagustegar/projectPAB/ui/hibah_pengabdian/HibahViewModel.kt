package com.l0122030.bagustegar.projectPAB.ui.hibah_pengabdian

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0122030.bagustegar.projectPAB.data.api.ApiConfig
import com.l0122030.bagustegar.projectPAB.data.model.HibahPengabdian
import com.l0122030.bagustegar.projectPAB.data.model.HibahPengabdianResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HibahViewModel : ViewModel() {

    private var _uiState: MutableLiveData<HibahState> = MutableLiveData(HibahState.Loading)
    val uiState: LiveData<HibahState> get() = _uiState

    init {
        getHibahPengabdian()
    }

    private fun getHibahPengabdian() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiConfig.getApiService().getHibahPengabdian()
                val result = response.documents?.map {
                    HibahPengabdian(
                        faculty = it.fields?.faculty?.stringValue.orEmpty(),
                        value = it.fields?.value?.integerValue?.toInt() ?: 0
                    )
                } ?: emptyList()

                if (result.isNotEmpty()) {
                    _uiState.postValue(HibahState.Success(result))
                } else {
                    _uiState.postValue(HibahState.Error(Throwable("Data not found")))
                }
            } catch (e: Exception) {
                _uiState.postValue(HibahState.Error(e))
            }
        }
    }
}

sealed class HibahState {
    object Loading : HibahState()
    data class Success(val data: List<HibahPengabdian>) : HibahState()
    data class Error(val error: Throwable) : HibahState()
}