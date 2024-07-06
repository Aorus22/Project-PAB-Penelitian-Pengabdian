package com.projectPAB.data.model.keynote_speaker

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class KeynoteSpeakerRsponse(
    @field:SerializedName("documents")
    val documents: List<KeynoteSpeakeItem>? = emptyList()
) : Parcelable

@Parcelize
data class KeynoteSpeakerField(
    @field:SerializedName("Jumlah 2022")
    val jumlah2022: KeynoteSpeaker2022? = null,

    @field:SerializedName("Jumlah 2023")
    val jumlah2023: KeynoteSpeaker2023? = null,

    @field:SerializedName("Jumlah 2024")
    val jumlah2024: KeynoteSpeaker2024? = null,

    @field:SerializedName("Name")
    val keynotespeakername: KeynoteSpeakerName? = null
) : Parcelable

@Parcelize
data class KeynoteSpeakeItem(

    @field:SerializedName("createTime")
    val createTime: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("updateTime")
    val updateTime: String? = null,

    @field:SerializedName("fields")
    val fields: KeynoteSpeakerField? = null
) : Parcelable

@Parcelize
data class KeynoteSpeaker2022(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable

@Parcelize
data class KeynoteSpeaker2023(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class KeynoteSpeaker2024(

    @field:SerializedName("integerValue")
    val integerValue: String? = null
) : Parcelable


@Parcelize
data class KeynoteSpeakerName(
    @field:SerializedName("stringValue")
    val stringValue: String? = null
) : Parcelable
