package com.projectPAB.data.model.reviewer

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ReviewerResponse(
    @field:SerializedName("documents")
    val documents: List<ReviewerItem>? = emptyList()
) : Parcelable

@Parcelize
data class ReviewerFields(
    @field:SerializedName("Jumlah 2022")
    val jumlah2022: Reviewer2022? = null,

    @field:SerializedName("Jumlah 2023")
    val jumlah2023: Reviewer2023? = null,

    @field:SerializedName("Jumlah 2024")
    val jumlah2024: Reviewer2024? = null,

    @field:SerializedName("Name")
    val reviewername: ReviewerName? = null
) : Parcelable

@Parcelize
data class ReviewerItem(

    @field:SerializedName("createTime")
    val createTime: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("updateTime")
    val updateTime: String? = null,

    @field:SerializedName("fields")
    val fields: ReviewerFields? = null
) : Parcelable

@Parcelize
data class Reviewer2022(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable

@Parcelize
data class Reviewer2023(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class Reviewer2024(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class ReviewerName(
    @field:SerializedName("stringValue")
    val stringValue: String? = null
) : Parcelable
