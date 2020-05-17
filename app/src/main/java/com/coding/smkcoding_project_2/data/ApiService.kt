package com.coding.smkcoding_project_2.data

import com.coding.smkcoding_project_2.serialized.global.GlobalDataItem
import com.coding.smkcoding_project_2.serialized.global.GlobalDataMeninggal
import com.coding.smkcoding_project_2.serialized.global.GlobalDataPositif
import com.coding.smkcoding_project_2.serialized.global.GlobalDataSembuh
import com.coding.smkcoding_project_2.serialized.province.ProvinceDataItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET(".")
    fun getDataGlobal(): Call<List<GlobalDataItem>>

    @GET("indonesia/provinsi/")
    fun getDataProvince(): Call<List<ProvinceDataItem>>

    @GET("positif")
    fun getDataPositif(): Call<GlobalDataPositif>

    @GET("sembuh")
    fun getDataSembuh(): Call<GlobalDataSembuh>

    @GET("meninggal")
    fun getDataMeninggoy(): Call<GlobalDataMeninggal>


}
