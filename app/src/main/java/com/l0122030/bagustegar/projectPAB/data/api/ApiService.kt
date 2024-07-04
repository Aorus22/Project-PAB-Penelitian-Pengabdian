package com.l0122030.bagustegar.projectPAB.data.api

import com.l0122030.bagustegar.projectPAB.data.model.AktivitasGrupRisetResponse
import com.l0122030.bagustegar.projectPAB.data.model.AktivitasPenelitianResponse
import com.l0122030.bagustegar.projectPAB.data.model.HibahPengabdianResponse
import com.l0122030.bagustegar.projectPAB.data.model.ProsidingResponse
import com.l0122030.bagustegar.projectPAB.data.model.RekapResponse
import com.l0122030.bagustegar.projectPAB.data.model.SumberBiayaResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    @GET
    suspend fun getHibahPengabdian(
        @Url url: String? = "https://firestore.googleapis.com/v1/projects/project-pab-947de/databases/(default)/documents/hibah-pengabdian"
    ): HibahPengabdianResponse

    @GET
    suspend fun getAktivitasPenelitian(
        @Url url: String? = "https://firestore.googleapis.com/v1/projects/project-pab-947de/databases/(default)/documents/aktivitas-penelitian"
    ): AktivitasPenelitianResponse

    @GET
    suspend fun getAktivitasGrupRiset(
        @Url url: String? = "https://firestore.googleapis.com/v1/projects/project-pab-947de/databases/(default)/documents/aktivitas-grup-riset"
    ): AktivitasGrupRisetResponse

    @GET
    suspend fun  getRekap(
        @Url url : String? = "https://firestore.googleapis.com/v1/projects/project-pab-12844/databases/(default)/documents/Rekap"
    ) : RekapResponse

    @GET
    suspend fun  getSumberBiaya(
        @Url url : String? = "https://firestore.googleapis.com/v1/projects/project-pab-12844/databases/(default)/documents/Sumber%20Biaya"
    ) : SumberBiayaResponse

    @GET
    suspend fun  getProsiding(
        @Url url : String? = "https://firestore.googleapis.com/v1/projects/project-pab-12844/databases/(default)/documents/Prosiding"
    ) : ProsidingResponse
}