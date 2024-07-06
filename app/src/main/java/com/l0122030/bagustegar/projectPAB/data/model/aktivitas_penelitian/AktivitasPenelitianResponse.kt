package com.l0122030.bagustegar.projectPAB.data.model.aktivitas_penelitian

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class AktivitasPenelitianResponse(

	@field:SerializedName("documents")
	val documents: List<AktivitasPenelitianDocumentsItem?>? = null
) : Parcelable

@Parcelize
data class AktivitasPenelitianDocumentsItem(

	@field:SerializedName("createTime")
	val createTime: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("updateTime")
	val updateTime: String? = null,

	@field:SerializedName("fields")
	val fields: AktivitasPenelitianFields? = null
) : Parcelable

@Parcelize
data class AktivitasPenelitianValue(

	@field:SerializedName("integerValue")
	val integerValue: String? = null
) : Parcelable

@Parcelize
data class AktivitasPenelitianFields(

    @field:SerializedName("year")
	val year: AktivitasPenelitianYear? = null,

    @field:SerializedName("value")
	val value: AktivitasPenelitianValue? = null
) : Parcelable

@Parcelize
data class AktivitasPenelitianYear(

	@field:SerializedName("integerValue")
	val integerValue: String? = null
) : Parcelable
