package com.coding.smkcoding_project_2.serialized


import com.google.gson.annotations.SerializedName

data class PerProvinsi(
    @SerializedName("csv")
    val csv: String,
    @SerializedName("json")
    val json: String
)