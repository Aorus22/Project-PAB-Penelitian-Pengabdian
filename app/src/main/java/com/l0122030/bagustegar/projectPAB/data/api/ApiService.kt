package com.l0122030.bagustegar.projectPAB.data.api

import com.l0122030.bagustegar.projectPAB.data.model.aktivitas_grup_riset.AktivitasGrupRisetResponse
import com.l0122030.bagustegar.projectPAB.data.model.aktivitas_penelitian.AktivitasPenelitianResponse
import com.l0122030.bagustegar.projectPAB.data.model.haki.HakiResponse
import com.l0122030.bagustegar.projectPAB.data.model.hibah_pengabdian.HibahPengabdianResponse
import com.l0122030.bagustegar.projectPAB.data.model.koran.KoranResponse
import com.l0122030.bagustegar.projectPAB.data.model.penelitian.PenelitianItem
import com.l0122030.bagustegar.projectPAB.data.model.penelitian.PenelitianResponse
import com.l0122030.bagustegar.projectPAB.data.model.program_kerja.ProgramKerjaResponse
import com.l0122030.bagustegar.projectPAB.data.model.prosiding.ProsidingResponse
import com.l0122030.bagustegar.projectPAB.data.model.rekap.RekapResponse
import com.l0122030.bagustegar.projectPAB.data.model.sumber_biaya.SumberBiayaResponse
import com.projectPAB.data.model.keynote_speaker.KeynoteSpeakerRsponse
import com.projectPAB.data.model.reviewer.ReviewerResponse
import retrofit2.http.GET
import retrofit2.http.Url

interface ApiService {
    //Bagus
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

    // Ananta
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

    // Nadif
    @GET
    suspend fun  getPenelitian(
        @Url url : String? = "https://firestore.googleapis.com/v1/projects/project-pab-12844/databases/(default)/documents/Penelitian"
    ) : PenelitianResponse

    @GET
    suspend fun getPenelitianByFakultas(
        @Url url: String
    ): PenelitianItem

    @GET
    suspend fun  getKoran(
        @Url url : String? = "https://firestore.googleapis.com/v1/projects/project-pab-12844/databases/(default)/documents/Koran"
    ) : KoranResponse

    @GET
    suspend fun  getHaki(
        @Url url : String? = "https://firestore.googleapis.com/v1/projects/project-pab-12844/databases/(default)/documents/Haki"
    ) : HakiResponse

    @GET
    suspend fun  getProgramKerja(
        @Url url : String? = "https://firestore.googleapis.com/v1/projects/project-pab-12844/databases/(default)/documents/Program%20Kerja"
    ) : ProgramKerjaResponse

    // Que
    @GET
    suspend fun  getKeynoteSpeaker(
        @Url url : String? = "https://firestore.googleapis.com/v1/projects/project-pab-12844/databases/(default)/documents/Keynote%20Speaker"
    ) : KeynoteSpeakerRsponse

    @GET
    suspend fun  getReviewer(
        @Url url : String? = "https://firestore.googleapis.com/v1/projects/project-pab-12844/databases/(default)/documents/Reviewer"
    ) : ReviewerResponse

}