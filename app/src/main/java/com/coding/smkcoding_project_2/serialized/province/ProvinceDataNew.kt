package com.coding.smkcoding_project_2.serialized.province


import com.google.gson.annotations.SerializedName

data class ProvinceDataNew(
    @SerializedName("data")
    val `data`: List<Data>
)