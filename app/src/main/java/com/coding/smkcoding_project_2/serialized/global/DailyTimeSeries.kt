package com.coding.smkcoding_project_2.serialized.global


import com.google.gson.annotations.SerializedName

data class DailyTimeSeries(
    @SerializedName("example")
    val example: String,
    @SerializedName("pattern")
    val pattern: String
)