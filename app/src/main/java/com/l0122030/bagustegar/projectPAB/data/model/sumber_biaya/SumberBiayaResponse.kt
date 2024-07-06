package com.l0122030.bagustegar.projectPAB.data.model.sumber_biaya

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class SumberBiayaResponse(
    @field:SerializedName("documents")
    val documents: List<SumberBiayaItem>? = emptyList()
) : Parcelable

@Parcelize
data class SumberBiayaFields(
    @field:SerializedName("Jumlah 2022")
    val jumlah2022: SumberBiaya2022? = null,

    @field:SerializedName("Jumlah 2023")
    val jumlah2023: SumberBiaya2023? = null,

    @field:SerializedName("Jumlah 2024")
    val jumlah2024: SumberBiaya2024? = null,

    @field:SerializedName("Name")
    val biayaname: SumberBiayaName? = null
) : Parcelable

@Parcelize
data class SumberBiayaItem(

    @field:SerializedName("createTime")
    val createTime: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("updateTime")
    val updateTime: String? = null,

    @field:SerializedName("fields")
    val fields: SumberBiayaFields? = null
) : Parcelable

@Parcelize
data class SumberBiaya2022(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable

@Parcelize
data class SumberBiaya2023(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class SumberBiaya2024(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class SumberBiayaName(
    @field:SerializedName("stringValue")
    val stringValue: String? = null
) : Parcelable
