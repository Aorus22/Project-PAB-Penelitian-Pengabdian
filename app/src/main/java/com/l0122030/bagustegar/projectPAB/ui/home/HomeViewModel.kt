package com.l0122030.bagustegar.projectPAB.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.l0122030.bagustegar.projectPAB.data.api.ApiConfig
import com.l0122030.bagustegar.projectPAB.data.model.AktivitasGrupRiset
import com.l0122030.bagustegar.projectPAB.data.model.AktivitasPenelitian
import com.l0122030.bagustegar.projectPAB.data.model.HibahPengabdian
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _aktivitasPenelitian = MutableLiveData<Resource<AktivitasPenelitian>>(Resource.Loading)
    val aktivitasPenelitian: LiveData<Resource<AktivitasPenelitian>>
        get() = _aktivitasPenelitian

    private val _aktivitasGrupRiset = MutableLiveData<Resource<AktivitasGrupRiset>>(Resource.Loading)
    val aktivitasGrupRiset: LiveData<Resource<AktivitasGrupRiset>>
        get() = _aktivitasGrupRiset

    private val _aktivitasHibah = MutableLiveData<Resource<HibahPengabdian>>(Resource.Loading)
    val aktivitasHibah: LiveData<Resource<HibahPengabdian>>
        get() = _aktivitasHibah

    private val apiService = ApiConfig.getApiService()

    init {
        getAktivitasPenelitian()
        getAktivitasGrupRiset()
        getAktivitasHibah()
    }

    private fun getAktivitasHibah() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getHibahPengabdian()
                val result = response.documents?.map {
                    HibahPengabdian(
                        faculty = it.fields?.faculty?.stringValue ?: "",
                        value = it.fields?.value?.integerValue?.toInt() ?: 0
                    )
                } ?: emptyList()

                if (result.isNotEmpty()) {
                    _aktivitasHibah.postValue(Resource.Success(result))
                } else {
                    _aktivitasHibah.postValue(Resource.Error("Data is Empty"))
                }
            } catch (e: Exception) {
                _aktivitasHibah.postValue(Resource.Error(e.message.toString()))
            }

        }
    }

    private fun getAktivitasGrupRiset() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getAktivitasGrupRiset()
                val result = response.documents?.map {
                    AktivitasGrupRiset(
                        name = it?.fields?.name?.stringValue ?: "",
                        value = it?.fields?.value?.integerValue?.toInt() ?: 0
                    )
                } ?: emptyList()

                if (result.isNotEmpty()) {
                    _aktivitasGrupRiset.postValue(Resource.Success(result))
                } else {
                    _aktivitasGrupRiset.postValue(Resource.Error("Data is Empty"))
                }
            } catch (e: Exception) {
                _aktivitasGrupRiset.postValue(Resource.Error(e.message.toString()))
            }

        }
    }

    private fun getAktivitasPenelitian() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getAktivitasPenelitian()
                val result = response.documents?.map {
                    AktivitasPenelitian(
                        year = it?.fields?.year?.integerValue?.toInt() ?: 0,
                        value = it?.fields?.value?.integerValue?.toInt() ?: 0
                    )
                } ?: emptyList()

                if (result.isNotEmpty()) {
                    _aktivitasPenelitian.postValue(Resource.Success(result))
                } else {
                    _aktivitasPenelitian.postValue(Resource.Error("Data is Empty"))
                }
            } catch (e: Exception) {
                _aktivitasPenelitian.postValue(Resource.Error(e.message.toString()))
            }

        }
    }
}

sealed class Resource<out T> {
    data class Success<T>(val data: List<T>) : Resource<T>()
    data class Error(val message: String) : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
}