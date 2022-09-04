package com.example.bagstore.ui.features.singInScreen


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.bagstore.model.repository.user.UserRepository
import java.io.Closeable

class SingInViewModel(private val userRepository: UserRepository) : ViewModel() {

    val emailState = MutableLiveData("")
    val passwordState = MutableLiveData("")

    val errorStateForEmail = MutableLiveData(false)
    val errorStateForPassword = MutableLiveData(false)

    fun resetStates() {
        emailState.value = ""
        passwordState.value = ""

        errorStateForPassword.value = false
        errorStateForEmail.value = false
    }


}