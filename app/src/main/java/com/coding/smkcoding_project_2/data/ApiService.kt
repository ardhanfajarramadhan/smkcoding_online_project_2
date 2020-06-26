package com.coding.smkcoding_project_2.data

import com.coding.smkcoding_project_2.serialized.IndonesiaDataItem
import com.coding.smkcoding_project_2.serialized.IndonesiaDataNew
import com.coding.smkcoding_project_2.serialized.global.*
import com.coding.smkcoding_project_2.serialized.province.Data
import com.coding.smkcoding_project_2.serialized.province.ProvinceDataItem
import com.coding.smkcoding_project_2.serialized.province.ProvinceDataNew
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("api/confirmed")
    fun getDataGlobal(): Call<GlobalDataNew>

    @GET("api/provinsi")
    fun getDataProvince(): Call<ProvinceDataNew>

    @GET("api")
    fun getDataIndonesia(): Call<IndonesiaDataNew>

    @GET("api")
    fun getDataTotalWorld(): Call<getGlobalDataNew>

//    @GET("api")
//    fun getDataSembuhNew(): Call<getGlobalDataNew>
//
//    @GET("api")
//    fun getDataMeninggoyNew(): Call<getGlobalDataNew>


}
