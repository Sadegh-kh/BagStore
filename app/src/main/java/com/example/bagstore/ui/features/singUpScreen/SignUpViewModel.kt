package com.example.bagstore.ui.features.singUpScreen


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore.model.repository.user.UserRepository
import com.example.bagstore.util.coroutineExceptionHandler
import kotlinx.coroutines.launch

class SignUpViewModel(private val userRepository: UserRepository) : ViewModel() {
    val nameState = MutableLiveData("")
    val emailState = MutableLiveData("")
    val passwordState = MutableLiveData("")
    val configPasswordState = MutableLiveData("")

    val errorStateForName = MutableLiveData(false)
    val errorStateForEmail = MutableLiveData(false)
    val errorStateForPassword = MutableLiveData(false)
    val errorStateForConfigPassword = MutableLiveData(false)

    val progressState=MutableLiveData(false)

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

    fun signUp(LoginEvent:(String)->Unit){
        viewModelScope.launch (context = coroutineExceptionHandler) {
           val messageFormServer= userRepository.signUp(nameState.value!!,emailState.value!!, passwordState.value!!)
            LoginEvent(messageFormServer)
        }.also {
            //this bring progress state for show it
            if (it.isActive){
                progressState.value=true
            }else if(it.isCompleted){
                progressState.value=false
            }else if(it.isCancelled){
                progressState.value=false
            }
        }
    }

}