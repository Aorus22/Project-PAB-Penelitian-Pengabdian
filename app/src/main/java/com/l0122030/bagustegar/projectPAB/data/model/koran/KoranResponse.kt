package com.l0122030.bagustegar.projectPAB.data.model.koran

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class KoranResponse(
    @field:SerializedName("documents")
    val documents: List<KoranItem>? = emptyList()
) : Parcelable

@Parcelize
data class KoranFields(
    @field:SerializedName("Jumlah 2022")
    val jumlah2022: Koran2022? = null,

    @field:SerializedName("Jumlah 2023")
    val jumlah2023: Koran2023? = null,

    @field:SerializedName("Jumlah 2024")
    val jumlah2024: Koran2024? = null,

    @field:SerializedName("Name")
    val koranname: KoranName? = null
) : Parcelable

@Parcelize
data class KoranItem(

    @field:SerializedName("createTime")
    val createTime: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("updateTime")
    val updateTime: String? = null,

    @field:SerializedName("fields")
    val fields: KoranFields? = null
) : Parcelable

@Parcelize
data class Koran2022(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable

@Parcelize
data class Koran2023(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class Koran2024(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class KoranName(
    @field:SerializedName("stringValue")
    val stringValue: String? = null
) : Parcelable
