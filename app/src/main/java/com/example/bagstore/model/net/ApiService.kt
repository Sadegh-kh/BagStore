package com.example.bagstore.model.net

import com.example.bagstore.model.data.LoginResponse
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {

    @POST("singUp")
    suspend fun singUp(@Body jsonObject: JsonObject):LoginResponse

    @POST("singIn")
    suspend fun singIn(@Body jsonObject: JsonObject):LoginResponse
}