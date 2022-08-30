package com.example.bagstore.util

sealed class MyScreens(val route:String){

    object MainScreen:MyScreens("mainScreen")
    object IntroScreen:MyScreens("introScreen")
    object SingUpScreen:MyScreens("singUpScreen")
    object SingInScreen:MyScreens("singInScreen")
}
