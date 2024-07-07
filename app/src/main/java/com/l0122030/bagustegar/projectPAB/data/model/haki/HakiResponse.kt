package com.l0122030.bagustegar.projectPAB.data.model.haki

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HakiResponse(
    @field:SerializedName("documents")
    val documents: List<HakiItem>? = emptyList()
) : Parcelable

@Parcelize
data class HakiFields(
    @field:SerializedName("Jumlah 2022")
    val jumlah2022: Haki2022? = null,

    @field:SerializedName("Jumlah 2023")
    val jumlah2023: Haki2023? = null,

    @field:SerializedName("Jumlah 2024")
    val jumlah2024: Haki2024? = null,

    @field:SerializedName("Name")
    val hakiname: HakiName? = null
) : Parcelable

@Parcelize
data class HakiItem(

    @field:SerializedName("createTime")
    val createTime: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("updateTime")
    val updateTime: String? = null,

    @field:SerializedName("fields")
    val fields: HakiFields? = null
) : Parcelable

@Parcelize
data class Haki2022(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable

@Parcelize
data class Haki2023(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class Haki2024(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class HakiName(
    @field:SerializedName("stringValue")
    val stringValue: String? = null
) : Parcelable
