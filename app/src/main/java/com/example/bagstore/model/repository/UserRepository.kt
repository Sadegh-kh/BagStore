package com.example.bagstore.model.repository

interface UserRepository {

    //online methods =>
    suspend fun singUp(name:String,emailAddress:String,password:String)
    suspend fun singIn(emailAddress: String,password: String)


    //offline methods =>
    fun singOut()
    //load token from Shared Preferences and caching
    fun loadToken()

    //save token into Shared Preferences
    fun saveToken(newToken:String)
    fun getToken():String

    fun saveUserName(userName:String)
    fun getUserName():String



}