package com.coding.smkcoding_project_2.serialized.global


import com.google.gson.annotations.SerializedName

data class GlobalDataPositif(
    @SerializedName("name")
    val name: String,
    @SerializedName("value")
    val value: String
)