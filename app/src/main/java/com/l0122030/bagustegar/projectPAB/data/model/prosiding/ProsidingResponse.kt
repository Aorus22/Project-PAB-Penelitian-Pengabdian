package com.l0122030.bagustegar.projectPAB.data.model.prosiding

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProsidingResponse(
    @field:SerializedName("documents")
    val documents: List<ProsidingItem>? = emptyList()
) : Parcelable

@Parcelize
data class ProsidingFields(
    @field:SerializedName("Jumlah 2022")
    val jumlah2022: Prosiding2022? = null,

    @field:SerializedName("Jumlah 2023")
    val jumlah2023: Prosiding2023? = null,

    @field:SerializedName("Jumlah 2024")
    val jumlah2024: Prosiding2024? = null,

    @field:SerializedName("Name")
    val prosidingname: ProsidingName? = null
) : Parcelable

@Parcelize
data class ProsidingItem(

    @field:SerializedName("createTime")
    val createTime: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("updateTime")
    val updateTime: String? = null,

    @field:SerializedName("fields")
    val fields: ProsidingFields? = null
) : Parcelable

@Parcelize
data class Prosiding2022(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable

@Parcelize
data class Prosiding2023(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class Prosiding2024(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class ProsidingName(
    @field:SerializedName("stringValue")
    val stringValue: String? = null
) : Parcelable
