package com.coding.smkcoding_project_2.serialized


import com.google.gson.annotations.SerializedName

data class PerKasus(
    @SerializedName("csv")
    val csv: String,
    @SerializedName("json")
    val json: String,
    @SerializedName("links")
    val links: String,
    @SerializedName("old")
    val old: String
)