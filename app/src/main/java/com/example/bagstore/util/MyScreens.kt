package com.example.bagstore.util

sealed class MyScreens(val route:String){

    object MainScreen:MyScreens("mainScreen")
    object IntroScreen:MyScreens("introScreen")
    object SingUpScreen:MyScreens("signUpScreen")
    object SingInScreen:MyScreens("signInScreen")
    object ProfileScreen:MyScreens("profileScreen")
    object ShoppingCardScreen:MyScreens("shoppingCardScreen")
    object CategoryScreen:MyScreens("categoryScreen")
}
