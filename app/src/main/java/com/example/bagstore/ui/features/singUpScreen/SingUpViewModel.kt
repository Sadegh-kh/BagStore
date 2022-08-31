package com.example.bagstore.ui.features.singUpScreen


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SingUpViewModel:ViewModel() {
    val nameState = MutableLiveData("")
    val emailState =MutableLiveData("")
    val passwordState = MutableLiveData("")
    val configPasswordState =MutableLiveData("")
}