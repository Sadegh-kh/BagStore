package com.example.bagstore.ui.features.singInScreen


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SingInViewModel:ViewModel() {
    val emailState =MutableLiveData("")
    val passwordState = MutableLiveData("")
}