package com.example.bagstore.ui.features.signInScreen


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore.model.repository.user.UserRepository
import com.example.bagstore.util.coroutineExceptionHandler
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class SignInViewModel(private val userRepository: UserRepository) : ViewModel() {

    val emailState = MutableLiveData("")
    val passwordState = MutableLiveData("")

    val errorStateForEmail = MutableLiveData(false)
    val errorStateForPassword = MutableLiveData(false)

    val progressState=MutableLiveData(false)

    fun resetStates() {
        emailState.value = ""
        passwordState.value = ""

        errorStateForPassword.value = false
        errorStateForEmail.value = false
    }

    fun signIn(LoginEvent:(String)->Unit): Job {
        val jobInfo=viewModelScope.launch (context = coroutineExceptionHandler) {
            val messageFormServer=userRepository.signIn(emailState.value!!, passwordState.value!!)
            LoginEvent(messageFormServer)
        }
        return jobInfo
    }


}