package com.l0122030.bagustegar.projectPAB.data.model.rekap

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class RekapResponse(
    @field:SerializedName("documents")
    val documents: List<RekapItem>? = emptyList()
) : Parcelable

@Parcelize
data class RekapFields(
    @field:SerializedName("Jumlah 2022")
    val jumlah2022: Rekap2022? = null,

    @field:SerializedName("Jumlah 2023")
    val jumlah2023: Rekap2023? = null,

    @field:SerializedName("Jumlah 2024")
    val jumlah2024: Rekap2024? = null,

    @field:SerializedName("Name")
    val rekapname: RekapName? = null
) : Parcelable

@Parcelize
data class RekapItem(

    @field:SerializedName("createTime")
    val createTime: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("updateTime")
    val updateTime: String? = null,

    @field:SerializedName("fields")
    val fields: RekapFields? = null
) : Parcelable

@Parcelize
data class Rekap2022(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable

@Parcelize
data class Rekap2023(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class Rekap2024(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class RekapName(
    @field:SerializedName("stringValue")
    val stringValue: String? = null
) : Parcelable
