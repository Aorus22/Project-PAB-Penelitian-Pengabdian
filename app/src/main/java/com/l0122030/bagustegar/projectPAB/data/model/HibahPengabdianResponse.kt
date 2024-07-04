package com.l0122030.bagustegar.projectPAB.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class HibahPengabdianResponse(

	@field:SerializedName("documents")
	val documents: List<DocumentsItem>? = emptyList()
) : Parcelable

@Parcelize
data class Fields(

	@field:SerializedName("value")
	val value: Value? = null,

	@field:SerializedName("faculty")
	val faculty: Faculty? = null
) : Parcelable

@Parcelize
data class DocumentsItem(

	@field:SerializedName("createTime")
	val createTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("updateTime")
	val updateTime: String? = null,

	@field:SerializedName("fields")
	val fields: Fields? = null
) : Parcelable

@Parcelize
data class Value(

	@field:SerializedName("integerValue")
	val integerValue: String? = null
) : Parcelable

@Parcelize
data class Faculty(

	@field:SerializedName("stringValue")
	val stringValue: String? = null
) : Parcelable
