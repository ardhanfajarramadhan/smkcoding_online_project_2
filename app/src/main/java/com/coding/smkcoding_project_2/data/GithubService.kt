package com.coding.smkcoding_project_2.data

import com.coding.smkcoding_project_2.GithubUserItem
import com.coding.smkcoding_project_2.serialized.GlobalData
import com.coding.smkcoding_project_2.serialized.GlobalDataItem
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {
    @GET("users")
    fun getUsers(): Call<List<GithubUserItem>>

    @GET("")
    fun getDataGlobal(): Call<List<GlobalDataItem>>
}
