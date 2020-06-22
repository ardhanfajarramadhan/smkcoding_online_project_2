package com.coding.smkcoding_project_2.serialized


import com.google.gson.annotations.SerializedName

data class IndonesiaDataNew(
    @SerializedName("jumlahKasus")
    val jumlahKasus: Int,
    @SerializedName("meninggal")
    val meninggal: Int,
    @SerializedName("perHari")
    val perHari: PerHari,
    @SerializedName("perKasus")
    val perKasus: PerKasus,
    @SerializedName("perProvinsi")
    val perProvinsi: PerProvinsi,
    @SerializedName("perawatan")
    val perawatan: Int,
    @SerializedName("sembuh")
    val sembuh: Int,
    @SerializedName("wismaAtlet")
    val wismaAtlet: WismaAtlet
)