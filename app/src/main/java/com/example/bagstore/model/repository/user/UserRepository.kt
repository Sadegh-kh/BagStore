package com.example.bagstore.model.repository.user

interface UserRepository {

    //online methods =>
    suspend fun singUp(name:String,emailAddress:String,password:String): String
    suspend fun singIn(emailAddress: String,password: String): String


    //offline methods =>
    //remove token and email address from Shared Preferences
    fun singOut()
    //load token from Shared Preferences and caching
    fun loadToken()

    //save token into Shared Preferences
    fun saveToken(newToken:String)
    fun getToken():String
    //save Email into Shared Preferences
    fun saveEmailAddress(emailAddress:String)
    fun getEmailAddress():String



}