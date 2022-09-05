package com.example.bagstore.ui.features.singInScreen


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore.model.repository.user.UserRepository
import kotlinx.coroutines.launch
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

    fun singIn(messageOfOperation:(String)->Unit){
        viewModelScope.launch {
            val messageFormServer=userRepository.singIn(emailState.value!!, passwordState.value!!)
            messageOfOperation(messageFormServer)
        }
    }


}