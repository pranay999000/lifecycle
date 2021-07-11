package com.example.livedata.api

import com.example.livedata.api.Link.Companion.LINK_URL
import com.example.livedata.model.server.Dictionary
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RestAPI {
    companion object {
        operator fun invoke(): RestAPI = Retrofit.Builder()
            .baseUrl(LINK_URL)
            .client(OkHttpClient.Builder().also { client ->
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
                client.addInterceptor(loggingInterceptor)
            }.build())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RestAPI::class.java)
    }

    @GET(".")
    suspend fun getDictionary():Response<Dictionary>
}