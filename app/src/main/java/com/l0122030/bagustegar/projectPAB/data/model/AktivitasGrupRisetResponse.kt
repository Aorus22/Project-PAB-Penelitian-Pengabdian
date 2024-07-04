package com.l0122030.bagustegar.projectPAB.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AktivitasGrupRisetResponse(

	@field:SerializedName("documents")
	val documents: List<RisetItem?>? = null
) : Parcelable

@Parcelize
data class RisetFields(

	@field:SerializedName("name")
	val name: RisetName? = null,

	@field:SerializedName("value")
	val value: RisetValue? = null
) : Parcelable

@Parcelize
data class RisetItem(

	@field:SerializedName("createTime")
	val createTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("updateTime")
	val updateTime: String? = null,

	@field:SerializedName("fields")
	val fields: RisetFields? = null
) : Parcelable

@Parcelize
data class RisetValue(

	@field:SerializedName("integerValue")
	val integerValue: String? = null
) : Parcelable

@Parcelize
data class RisetName(

	@field:SerializedName("stringValue")
	val stringValue: String? = null
) : Parcelable
