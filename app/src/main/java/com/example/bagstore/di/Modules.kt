package com.example.bagstore.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.bagstore.model.net.ApiService
import com.example.bagstore.model.repository.TokenInMemory
import com.example.bagstore.model.repository.comment.CommentRepository
import com.example.bagstore.model.repository.comment.CommentRepositoryImp
import com.example.bagstore.model.repository.local.MyDatabase
import com.example.bagstore.model.repository.local.ProductDao
import com.example.bagstore.model.repository.product.ProductRepository
import com.example.bagstore.model.repository.product.ProductRepositoryImp
import com.example.bagstore.model.repository.user.UserRepository
import com.example.bagstore.model.repository.user.UserRepositoryImp
import com.example.bagstore.ui.features.categoryScreen.CategoryScreenViewModel
import com.example.bagstore.ui.features.mainScreen.MainScreenViewModel
import com.example.bagstore.ui.features.productScreen.ProductScreenViewModel
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

    //database
    single<MyDatabase> {
        Room.databaseBuilder(
            androidContext(),
            MyDatabase::class.java,
            "myDatabase.db"
        ).build()
    }
    //product dao
    single<ProductDao> {
        val myDatabase=get<MyDatabase>()
        myDatabase.productDao
    }

    //shared preferences
    single<SharedPreferences> {
        androidContext().getSharedPreferences("usersInfo",Context.MODE_PRIVATE)
    }

    //okhttp for header of api request
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

    //api service
    single<ApiService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
            .create(ApiService::class.java)
    }

    //user repo
    single<UserRepository> {
        UserRepositoryImp(get(),get())
    }

    //product repo
    single<ProductRepository> {
        ProductRepositoryImp(get(),get())
    }

    //comment repo
    single<CommentRepository> {
        CommentRepositoryImp(get())
    }

    //view models
    viewModel { SignUpViewModel(get()) }
    viewModel { SignInViewModel(get()) }

    //get net condition from entry
    viewModel { (isNetConnected:Boolean)-> MainScreenViewModel(get(),isNetConnected) }
    viewModel { (categoryName:String)->CategoryScreenViewModel(categoryName,get()) }

    viewModel { (productId:String)->ProductScreenViewModel(productId,get(),get()) }

}