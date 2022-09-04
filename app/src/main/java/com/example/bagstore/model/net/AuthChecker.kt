package com.example.bagstore.model.net

import com.example.bagstore.model.data.LoginResponse
import com.example.bagstore.model.repository.TokenInMemory
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class AuthChecker: Authenticator,KoinComponent {
    private val apiService:ApiService by inject()
    override fun authenticate(route: Route?, response: Response): Request? {
        if (TokenInMemory.token!=null
            &&
            !response.request.url.pathSegments.last().equals("refreshToken",false)
        ){
            val result=updateToken()
            if (result){
                return response.request
            }

        }
        return null
    }

    private fun updateToken():Boolean{
        val response:retrofit2.Response<LoginResponse> = apiService.refreshToken().execute()
        if (response.body()!=null){
            if (response.body()!!.success){
                return true
            }
        }
        return false
    }
}