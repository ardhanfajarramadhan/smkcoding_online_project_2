package com.coding.smkcoding_project_2.serialized


import com.google.gson.annotations.SerializedName

data class WismaAtlet(
    @SerializedName("json")
    val json: String,
    @SerializedName("karyawan")
    val karyawan: String,
    @SerializedName("kasur")
    val kasur: String,
    @SerializedName("ruangan")
    val ruangan: String
)