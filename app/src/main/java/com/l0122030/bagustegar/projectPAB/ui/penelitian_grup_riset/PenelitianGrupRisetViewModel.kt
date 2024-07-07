package com.l0122030.bagustegar.projectPAB.ui.penelitian_grup_riset

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.l0122030.bagustegar.projectPAB.data.api.ApiConfigGlobal
import com.l0122030.bagustegar.projectPAB.data.model.penelitian.PenelitianItem
import com.l0122030.bagustegar.projectPAB.data.model.penelitian_grup_riset.PenelitianGrupRiset
import com.l0122030.bagustegar.projectPAB.data.model.penelitian_grup_riset.PenelitianGrupRisetItem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PenelitianGrupRisetViewModel : ViewModel() {

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

    private val _faculties = MutableLiveData<List<PenelitianGrupRiset>>()
    val faculties: LiveData<List<PenelitianGrupRiset>> = _faculties

    init {
        fetchData()
    }

    private fun fetchData() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = ApiConfigGlobal.getApiService().getPenelitianGrupRiset()
                val facultiesList = response.documents.map { document ->
                    mapToPenelitianGrupRiset(document)
                }
                _faculties.postValue(facultiesList)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun mapToPenelitianGrupRiset(item: PenelitianGrupRisetItem): PenelitianGrupRiset {
        val pathSegments = item.name.split("/")
        val formattedFacultyName = pathSegments.last().replace("-", " ")
        val urlPenelitianFakultas = item.name.split("/").last()

        val totalPenelitian = item.fields.TotalPenelitian.integerValue.toIntOrNull() ?: 0

        return PenelitianGrupRiset(formattedFacultyName, totalPenelitian, urlPenelitianFakultas)
    }

}
