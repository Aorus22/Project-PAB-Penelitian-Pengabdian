package com.l0122030.bagustegar.projectPAB.data.api

import com.l0122030.bagustegar.projectPAB.data.model.AktivitasGrupRisetResponse
import com.l0122030.bagustegar.projectPAB.data.model.AktivitasPenelitianResponse
import com.l0122030.bagustegar.projectPAB.data.model.HibahPengabdianResponse
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
}