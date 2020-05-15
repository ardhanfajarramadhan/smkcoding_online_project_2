package com.coding.smkcoding_project_2.serialized.global


import com.google.gson.annotations.SerializedName

data class GlobalDataItem(
    @SerializedName("attributes")
    val attributes: Attributes
)