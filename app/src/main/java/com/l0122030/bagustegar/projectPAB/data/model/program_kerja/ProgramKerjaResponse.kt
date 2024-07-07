package com.l0122030.bagustegar.projectPAB.data.model.program_kerja

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProgramKerjaResponse(
    @field:SerializedName("documents")
    val documents: List<ProgramKerjaItem>? = emptyList()
) : Parcelable

@Parcelize
data class ProgramKerjaFields(
    @field:SerializedName("Jumlah 2022")
    val jumlah2022: ProgramKerja2022? = null,

    @field:SerializedName("Jumlah 2023")
    val jumlah2023: ProgramKerja2023? = null,

    @field:SerializedName("Jumlah 2024")
    val jumlah2024: ProgramKerja2024? = null,

    @field:SerializedName("Name")
    val programkerjaname: ProgramKerjaName? = null
) : Parcelable

@Parcelize
data class ProgramKerjaItem(

    @field:SerializedName("createTime")
    val createTime: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("updateTime")
    val updateTime: String? = null,

    @field:SerializedName("fields")
    val fields: ProgramKerjaFields? = null
) : Parcelable

@Parcelize
data class ProgramKerja2022(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable

@Parcelize
data class ProgramKerja2023(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class ProgramKerja2024(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class ProgramKerjaName(
    @field:SerializedName("stringValue")
    val stringValue: String? = null
) : Parcelable
