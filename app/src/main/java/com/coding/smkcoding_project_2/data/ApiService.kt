package com.coding.smkcoding_project_2.data

import com.coding.smkcoding_project_2.GithubUserItem
import com.coding.smkcoding_project_2.serialized.global.GlobalDataItem
import com.coding.smkcoding_project_2.serialized.province.ProvinceDataItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    fun getUsers(): Call<List<GithubUserItem>>

    @GET(".")
    fun getDataGlobal(): Call<List<GlobalDataItem>>

    @GET("indonesia/provinsi/")
    fun getDataProvince(): Call<List<ProvinceDataItem>>
}
