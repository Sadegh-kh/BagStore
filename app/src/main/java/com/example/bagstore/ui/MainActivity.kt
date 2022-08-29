package com.example.bagstore.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bagstore.ui.features.IntroScreen
import com.example.bagstore.ui.theme.BackgroundMain
import com.example.bagstore.ui.theme.MainAppTheme
import com.example.bagstore.util.MyScreens

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        setContent {
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

@Composable
fun BagStoreUi(){

    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = MyScreens.IntroScreen.route){

        composable(MyScreens.MainScreen.route){
            MainScreen()
        }

        composable(MyScreens.IntroScreen.route){
            IntroScreen()
        }
    }
}

@Composable
fun MainScreen(){
    MainAppTheme {
        Surface(modifier = Modifier.fillMaxSize()){

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