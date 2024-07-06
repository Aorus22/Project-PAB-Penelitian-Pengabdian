package com.l0122030.bagustegar.projectPAB.data.model.penelitian

data class PenelitianResponse(
    val documents: List<PenelitianItem>
)

data class PenelitianItem(
    val name: String,
    val fields: Fields,
)

data class Fields(
    val TahunPenelitian: TahunPenelitian,
    val TotalPenelitian: TotalPenelitian,
    val NamaFakultas: NamaFakultas
)

data class TahunPenelitian(
    val arrayValue: ArrayValue
)

data class ArrayValue(
    val values: List<MapValue>
)

data class MapValue(
    val mapValue: MapField
)

data class MapField(
    val fields: PenelitianFields
)

data class PenelitianFields(
    val tahun: Tahun,
    val jumlah: Jumlah
)

data class Tahun(
    val stringValue: String
)

data class Jumlah(
    val integerValue: String
)

data class TotalPenelitian(
    val integerValue: String
)

data class NamaFakultas(
    val stringValue: String
)
