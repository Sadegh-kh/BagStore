package com.example.bagstore.ui.features.singUpScreen


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore.model.repository.user.UserRepository
import kotlinx.coroutines.launch

class SingUpViewModel(private val userRepository: UserRepository) : ViewModel() {
    val nameState = MutableLiveData("")
    val emailState = MutableLiveData("")
    val passwordState = MutableLiveData("")
    val configPasswordState = MutableLiveData("")

    val errorStateForName = MutableLiveData(false)
    val errorStateForEmail = MutableLiveData(false)
    val errorStateForPassword = MutableLiveData(false)
    val errorStateForConfigPassword = MutableLiveData(false)

    fun resetStates() {
        nameState.value = ""
        emailState.value = ""
        passwordState.value = ""
        configPasswordState.value = ""

        errorStateForName.value = false
        errorStateForEmail.value = false
        errorStateForPassword.value = false
        errorStateForConfigPassword.value = false
    }

    fun singUp(messageOfOperation:(String)->Unit){
        viewModelScope.launch {
           val messageFormServer= userRepository.singUp(nameState.value!!,emailState.value!!, passwordState.value!!)
            messageOfOperation(messageFormServer)
        }
    }

}