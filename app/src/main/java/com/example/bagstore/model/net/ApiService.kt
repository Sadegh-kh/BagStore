package com.example.bagstore.model.net

import com.example.bagstore.model.data.*
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.http.*

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

    @POST("getComments")
    suspend fun getAllComment(@Body productId:JsonObject):CommentResponse

    @POST("addNewComment")
    suspend fun addNewComment(@Body newCommentJson:JsonObject):AddNewCommentResponse


}
