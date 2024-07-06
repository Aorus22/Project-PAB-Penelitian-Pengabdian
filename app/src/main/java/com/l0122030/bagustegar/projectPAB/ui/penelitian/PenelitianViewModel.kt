package com.l0122030.bagustegar.projectPAB.ui.penelitian

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.l0122030.bagustegar.projectPAB.R
import com.l0122030.bagustegar.projectPAB.data.api.ApiConfigGlobal
import com.l0122030.bagustegar.projectPAB.data.model.penelitian.Penelitian
import com.l0122030.bagustegar.projectPAB.data.model.penelitian.PenelitianItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PenelitianViewModel : ViewModel() {

//    private val _faculties = MutableLiveData<List<Triple<String, Int, Int>>>().apply {
//        value = listOf(
//            Triple("Teknik", 150, R.drawable.ic_placeholder),
//            Triple("Pertanian", 200, R.drawable.ic_placeholder),
//            Triple("MIPA", 180, R.drawable.ic_placeholder),
//            Triple("Kedokteran", 170, R.drawable.ic_placeholder),
//            Triple("Ilmu Pendidikan", 160, R.drawable.ic_placeholder),
//            Triple("Pasca Sarjana", 140, R.drawable.ic_placeholder),
//            Triple("Hukum", 130, R.drawable.ic_placeholder),
//            Triple("Ilmu Budaya", 120, R.drawable.ic_placeholder)
//        )
//    }
//
//    val faculties: LiveData<List<Triple<String, Int, Int>>> = _faculties

    private val _faculties = MutableLiveData<List<Penelitian>>()
    val faculties: LiveData<List<Penelitian>> = _faculties

    init {
        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiConfigGlobal.getApiService().getPenelitian()
                val facultiesList = response.documents.map { document ->
                    mapToPenelitian(document)
                }
                _faculties.postValue(facultiesList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun mapToPenelitian(item: PenelitianItem): Penelitian {
        val pathSegments = item.name.split("/")
        val formattedFacultyName = pathSegments.last().replace("-", " ")
        val urlPenelitianFakultas = item.name.split("/").last()

        val totalPenelitian = item.fields.TotalPenelitian.integerValue.toIntOrNull() ?: 0

        return Penelitian(formattedFacultyName, totalPenelitian, urlPenelitianFakultas)
    }

}
