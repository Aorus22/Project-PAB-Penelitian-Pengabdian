package com.l0122030.bagustegar.projectPAB.ui.reviewer

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0122030.bagustegar.projectPAB.data.api.ApiConfigGlobal
import com.projectPAB.data.model.reviewer.Reviewer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ReviewerViewModel : ViewModel() {

    private var _uiState: MutableLiveData<ReviewerState> = MutableLiveData(ReviewerState.Loading)
    val uiState: LiveData<ReviewerState> get() = _uiState

    init {
        getReviewer()
    }

    private fun getReviewer() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = ApiConfigGlobal.getApiService().getReviewer()
                val result = response.documents?.map {
                    Reviewer(
                        name = it.fields?.reviewername?.stringValue.orEmpty(),
                        jumlah2022 = it.fields?.jumlah2022?.integerValue?.toInt() ?: 0,
                        jumlah2023 = it.fields?.jumlah2023?.integerValue?.toInt() ?: 0,
                        jumlah2024 = it.fields?.jumlah2024?.integerValue?.toInt() ?: 0
                    )
                } ?: emptyList()

                if (result.isNotEmpty()) {
                    _uiState.postValue(ReviewerState.Success(result))
                } else {
                    _uiState.postValue(ReviewerState.Error(Throwable("Data not found")))
                }
            } catch (e: Exception) {
                _uiState.postValue(ReviewerState.Error(e))
            }
        }
    }
}

sealed class ReviewerState {
    object Loading : ReviewerState()
    data class Success(val data: List<Reviewer>) : ReviewerState()
    data class Error(val error: Throwable) : ReviewerState()
}