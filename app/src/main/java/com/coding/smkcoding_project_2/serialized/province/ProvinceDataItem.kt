package com.coding.smkcoding_project_2.serialized.province


import com.google.gson.annotations.SerializedName

data class ProvinceDataItem(
    @SerializedName("attributes")
    val attributes: Attributes
)