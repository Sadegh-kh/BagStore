package com.example.bagstore.model.net

import com.example.bagstore.model.data.AdvertisementsResponse
import com.example.bagstore.model.data.LoginResponse
import com.example.bagstore.model.data.ProductResponse
import com.example.bagstore.model.repository.TokenInMemory
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {

    //sign in and sing up screen api
    @POST("signUp")
    suspend fun singUp(@Body jsonObject: JsonObject):LoginResponse

    @POST("signIn")
    suspend fun singIn(@Body jsonObject: JsonObject):LoginResponse


    @GET("refreshToken")
    fun refreshToken():Call<LoginResponse>


    //main screen , product and advertisement api
    @GET("getProducts")
    suspend fun getAllProducts():ProductResponse

    @GET("getSliderPics")
    suspend fun getAllAdvertisements():AdvertisementsResponse


}
