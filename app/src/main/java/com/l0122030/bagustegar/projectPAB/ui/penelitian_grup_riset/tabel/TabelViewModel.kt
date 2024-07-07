package com.l0122030.bagustegar.projectPAB.ui.penelitian_grup_riset.tabel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.l0122030.bagustegar.projectPAB.data.api.ApiConfigGlobal
import com.l0122030.bagustegar.projectPAB.data.api.ApiService
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TabelViewModel : ViewModel() {

//    private val _data = MutableLiveData<List<Pair<String, String>>>().apply {
//        value = listOf(
//            Pair("2018", "10"),
//            Pair("2019", "15"),
//            Pair("2020", "12"),
//            Pair("2021", "20"),
//            Pair("2022", "18"),
//            Pair("2023", "25")
//        )
//    }
//
//    val data: LiveData<List<Pair<String, String>>> = _data

    private val _data = MutableLiveData<List<Pair<String, String>>>()
    val data: LiveData<List<Pair<String, String>>> = _data

    private val apiService = ApiConfigGlobal.getApiService()

    @OptIn(DelicateCoroutinesApi::class)
    fun getDataByFaculty(facultyName: String) {
        val url = "Penelitian Grup Riset/$facultyName"
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = apiService.getPenelitianGrupRisetByFakultas(url)
                val formattedData = response.fields.TahunPenelitian.arrayValue.values.map { mapValue ->
                    val tahun = mapValue.mapValue.fields.tahun.stringValue
                    val jumlah = mapValue.mapValue.fields.jumlah.integerValue
                    Pair(tahun, jumlah)
                }
                _data.postValue(formattedData)
            } catch (e: Exception) {

                _data.postValue(emptyList())
            }
        }
    }
}
