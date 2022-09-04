package com.example.bagstore.di

import android.content.Context
import android.content.SharedPreferences
import com.example.bagstore.model.net.ApiService
import com.example.bagstore.model.repository.user.UserRepository
import com.example.bagstore.model.repository.user.UserRepositoryImp
import com.example.bagstore.ui.features.singInScreen.SingInViewModel
import com.example.bagstore.ui.features.singUpScreen.SingUpViewModel
import com.example.bagstore.util.BASE_URL
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val myModules= module {

    single<SharedPreferences> {
        androidContext().getSharedPreferences("usersInfo",Context.MODE_PRIVATE)
    }
    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    single<UserRepository> {
        UserRepositoryImp(get(),get())
    }
viewModel { SingUpViewModel(get())}
viewModel { SingInViewModel(get()) }
}