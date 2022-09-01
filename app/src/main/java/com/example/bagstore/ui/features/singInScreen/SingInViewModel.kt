package com.example.bagstore.ui.features.singInScreen


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.io.Closeable

class SingInViewModel:ViewModel() {

    val emailState =MutableLiveData("")
    val passwordState = MutableLiveData("")

    val errorStateForEmail=MutableLiveData(false)
    val errorStateForPassword=MutableLiveData(false)



}