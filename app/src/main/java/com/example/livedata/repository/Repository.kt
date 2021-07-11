package com.example.livedata.repository

import com.example.livedata.api.RestAPI
import com.example.livedata.model.server.Dictionary
import retrofit2.Response

class Repository {
    private val restAPI = RestAPI()

    suspend fun getDictionary(): Response<Dictionary> {
        return restAPI.getDictionary()
    }
}