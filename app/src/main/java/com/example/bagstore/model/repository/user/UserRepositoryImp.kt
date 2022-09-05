package com.example.bagstore.model.repository.user

import android.content.SharedPreferences
import com.example.bagstore.model.net.ApiService
import com.example.bagstore.model.repository.TokenInMemory
import com.example.bagstore.util.KEY_EMAIL_ADDRESS
import com.example.bagstore.util.JsonProperty
import com.example.bagstore.util.KEY_TOKEN
import com.example.bagstore.util.VALUE_SUCCESS
import com.google.gson.JsonObject

class UserRepositoryImp(
    private val apiService: ApiService,
    private val sharedPreferences: SharedPreferences
) : UserRepository {
    override suspend fun signUp(name: String, emailAddress: String, password: String): String {
        val userJsonObject = JsonObject().apply {
            addProperty(JsonProperty.NAME, name)
            addProperty(JsonProperty.EMAIL, emailAddress)
            addProperty(JsonProperty.PASSWORD, password)
        }
        val response = apiService.singUp(userJsonObject)
        return if (response.success) {
            TokenInMemory.refreshToken(emailAddress, response.token)
            saveToken(response.token)
            saveEmailAddress(emailAddress)
            return VALUE_SUCCESS
        } else {
            response.message
        }
    }

    override suspend fun signIn(emailAddress: String, password: String): String {
        val userJsonObject = JsonObject().apply {
            addProperty(JsonProperty.EMAIL, emailAddress)
            addProperty(JsonProperty.PASSWORD, password)
        }
        val response = apiService.singIn(userJsonObject)
        return if (response.success) {
            TokenInMemory.refreshToken(emailAddress, response.token)
            saveToken(response.token)
            saveEmailAddress(emailAddress)
            return VALUE_SUCCESS
        } else {
            response.message
        }
    }

    //remove user but on server will be exist
    override fun signOut() {
        TokenInMemory.refreshToken(null, null)
        sharedPreferences.edit().clear().apply()
    }

    override fun loadToken() {
        TokenInMemory.refreshToken(getEmailAddress(), getToken())
    }

    override fun saveToken(newToken: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, newToken).apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    override fun saveEmailAddress(emailAddress: String) {
        sharedPreferences.edit().putString(KEY_EMAIL_ADDRESS, emailAddress).apply()
    }

    override fun getEmailAddress(): String? {
        return sharedPreferences.getString(KEY_EMAIL_ADDRESS, null)
    }
}