package com.coding.smkcoding_project_2.serialized.province


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("fid")
    val fid: Int,
    @SerializedName("kasusMeni")
    val kasusMeni: Int,
    @SerializedName("kasusPosi")
    val kasusPosi: Int,
    @SerializedName("kasusSemb")
    val kasusSemb: Int,
    @SerializedName("kodeProvi")
    val kodeProvi: Int,
    @SerializedName("provinsi")
    val provinsi: String
)