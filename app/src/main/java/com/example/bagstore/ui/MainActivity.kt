package com.example.bagstore.ui

import android.os.Bundle
import android.view.WindowManager
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
import com.example.bagstore.ui.features.IntroScreen
import com.example.bagstore.ui.features.singInScreen.SingInScreen
import com.example.bagstore.ui.features.singUpScreen.SingUpScreen
import com.example.bagstore.ui.theme.MainAppTheme
import com.example.bagstore.util.MyScreens
import dev.burnoo.cokoin.Koin
import dev.burnoo.cokoin.navigation.KoinNavHost

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
            Koin(appDeclaration = { modules(myModules)}) {
                MainAppTheme {
                    Surface(
                        modifier = Modifier.fillMaxSize()
                    ) {
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
    KoinNavHost(navController = navController, startDestination = MyScreens.IntroScreen.route) {

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
            SingUpScreen()
        }
        composable(MyScreens.SingInScreen.route) {
            SingInScreen()
        }
    }
}

@Composable
fun MainScreen() {
    MainAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

        }

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainAppTheme {
        BagStoreUi()
    }
}