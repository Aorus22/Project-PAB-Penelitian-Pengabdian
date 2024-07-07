package com.l0122030.bagustegar.projectPAB.ui.keynote_speaker

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0122030.bagustegar.projectPAB.data.api.ApiConfigGlobal
import com.projectPAB.data.model.keynote_speaker.KeynoteSpeaker
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class KeynoteSpeakerViewModel : ViewModel() {

    private var _uiState: MutableLiveData<KeynoteSpeakerState> = MutableLiveData(KeynoteSpeakerState.Loading)
    val uiState: LiveData<KeynoteSpeakerState> get() = _uiState

    init {
        fetchData()
    }

    private fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiConfigGlobal.getApiService().getKeynoteSpeaker()
                val result = response.documents?.map {
                    KeynoteSpeaker(
                        name = it.fields?.keynotespeakername?.stringValue.orEmpty(),
                        jumlah2022 = it.fields?.jumlah2022?.integerValue?.toInt() ?: 0,
                        jumlah2023 = it.fields?.jumlah2023?.integerValue?.toInt() ?: 0,
                        jumlah2024 = it.fields?.jumlah2024?.integerValue?.toInt() ?: 0
                    )
                } ?: emptyList()

                if (result.isNotEmpty()) {
                    _uiState.postValue(KeynoteSpeakerState.Success(result))
                } else {
                    _uiState.postValue(KeynoteSpeakerState.Error(Throwable("Data not found")))
                }
            } catch (e: Exception) {
                _uiState.postValue(KeynoteSpeakerState.Error(e))
            }
        }
    }
}

sealed class KeynoteSpeakerState {
    object Loading : KeynoteSpeakerState()
    data class Success(val data: List<KeynoteSpeaker>) : KeynoteSpeakerState()
    data class Error(val error: Throwable) : KeynoteSpeakerState()
}