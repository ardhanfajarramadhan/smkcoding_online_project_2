package com.coding.smkcoding_project_2.data

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun httpClient(): OkHttpClient {
    val logInterceptor = HttpLoggingInterceptor()
    logInterceptor.level = HttpLoggingInterceptor.Level.BODY
    val builder = OkHttpClient.Builder()
    builder.readTimeout(15, TimeUnit.SECONDS)
    builder.connectTimeout(15, TimeUnit.SECONDS)
    builder.addInterceptor(logInterceptor)
    return builder.build()
}
inline fun <reified T> apiRequest(okHttpClient: OkHttpClient): T {
    val gson = GsonBuilder().create()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.kawalcorona.com/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit.create(T::class.java)
}

inline fun <reified T> requestApi(okHttpClient: OkHttpClient): T {
    val gson = GsonBuilder().create()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://covid19.mathdro.id/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit.create(T::class.java)
}

inline fun <reified T> requestCovidIndo(okHttpClient: OkHttpClient): T {
    val gson = GsonBuilder().create()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://indonesia-covid-19-api.now.sh/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit.create(T::class.java)
}

inline fun <reified T> requestProvIndo(okHttpClient: OkHttpClient): T {
    val gson = GsonBuilder().create()
    val retrofit = Retrofit.Builder()
        .baseUrl("https://indonesia-covid-19.mathdro.id/")
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    return retrofit.create(T::class.java)
}