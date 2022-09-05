package com.example.bagstore.di

import android.content.Context
import android.content.SharedPreferences
import com.example.bagstore.model.net.ApiService
import com.example.bagstore.model.repository.TokenInMemory
import com.example.bagstore.model.repository.user.UserRepository
import com.example.bagstore.model.repository.user.UserRepositoryImp
import com.example.bagstore.ui.features.signInScreen.SignInViewModel
import com.example.bagstore.ui.features.singUpScreen.SignUpViewModel
import com.example.bagstore.util.BASE_URL
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val myModules= module {

    single<SharedPreferences> {
        androidContext().getSharedPreferences("usersInfo",Context.MODE_PRIVATE)
    }
    factory<okhttp3.OkHttpClient> {

        val okHttpClient=OkHttpClient.Builder()
            .addInterceptor {
                val oldRequest=it.request()
                val newRequest=oldRequest.newBuilder()

                if (TokenInMemory.token!=null){
                    newRequest.addHeader("Authorization",TokenInMemory.token!!)
                }

                newRequest.method(oldRequest.method,oldRequest.body)
                return@addInterceptor it.proceed(newRequest.build())
            }.build()

        return@factory okHttpClient
    }

    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get(okhttp3.OkHttpClient::class))
            .build()
            .create(ApiService::class.java)
    }

    single<UserRepository> {
        UserRepositoryImp(get(),get())
    }
viewModel { SignUpViewModel(get())}
viewModel { SignInViewModel(get()) }
}