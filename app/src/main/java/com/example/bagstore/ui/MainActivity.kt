package com.example.bagstore.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bagstore.di.myModules
import com.example.bagstore.model.repository.TokenInMemory
import com.example.bagstore.model.repository.user.UserRepository
import com.example.bagstore.ui.features.IntroScreen
import com.example.bagstore.ui.features.mainScreen.MainScreen
import com.example.bagstore.ui.features.profileScreen.ProfileScreen
import com.example.bagstore.ui.features.shoppingCardScreen.ShoppingCardScreen
import com.example.bagstore.ui.features.signInScreen.SignInScreen
import com.example.bagstore.ui.features.singUpScreen.SignUpScreen
import com.example.bagstore.ui.theme.MainAppTheme
import com.example.bagstore.util.MyScreens
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.get
import dev.burnoo.cokoin.navigation.KoinNavHost
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )*/
        setContent {
            Koin(appDeclaration = {
                androidContext(this@MainActivity)
                modules(myModules)}
            ) {
                MainAppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        val userRepository:UserRepository=get()
                        userRepository.loadToken()
                        BagStoreUi()
                    }

                }
            }
        }
    }
}

@Composable
fun BagStoreUi() {
    val navController = rememberNavController()

    val startDestinationScreen=if (TokenInMemory.token==null){
        MyScreens.IntroScreen.route
    }else{
        MyScreens.MainScreen.route
    }

    KoinNavHost(navController = navController, startDestination = startDestinationScreen) {

        composable(MyScreens.MainScreen.route) {
            MainScreen()
        }

        composable(MyScreens.IntroScreen.route) {
            IntroScreen(singInClick = {
                navController.navigate(MyScreens.SingInScreen.route)
            }, singUpClick = {
                navController.navigate(MyScreens.SingUpScreen.route)
            })
        }
        composable(MyScreens.SingUpScreen.route) {
            SignUpScreen()
        }
        composable(MyScreens.SingInScreen.route) {
            SignInScreen()
        }
        composable(MyScreens.ProfileScreen.route){
            ProfileScreen()
        }
        composable(MyScreens.ShoppingCardScreen.route){
            ShoppingCardScreen()
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainAppTheme {
//        BagStoreUi()
    }
}