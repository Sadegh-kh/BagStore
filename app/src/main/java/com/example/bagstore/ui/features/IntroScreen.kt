package com.example.bagstore.ui.features

import android.view.WindowManager
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.bagstore.R
import com.example.bagstore.ui.theme.BackgroundMain
import com.example.bagstore.ui.theme.Blue
import com.example.bagstore.ui.theme.MainAppTheme
import com.example.bagstore.ui.theme.Shapes
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController


@Composable
fun IntroScreen(singInClick: () -> Unit, singUpClick: () -> Unit) {
    val navigation= getNavController()

    val systemUiController= rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Blue,darkIcons = false)

    Image(
        painter = painterResource(id = R.drawable.img_intro),
        contentDescription = null,
        modifier = Modifier.fillMaxSize(),
        contentScale = ContentScale.Crop
    )
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.77f),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ButtonSign(text = "Sing Up", backgroundColor = Blue, textColor = Color.White,singUpClick)
        ButtonSign(text = "Sing In", backgroundColor = Color.White, textColor = Blue,singInClick)

    }

}

@Composable
fun ButtonSign(text: String, backgroundColor: Color
               , textColor: Color
               , onClickButton: () -> Unit) {
    Button(
        onClick = { onClickButton.invoke() }, colors = ButtonDefaults.buttonColors(backgroundColor),
        modifier = Modifier.fillMaxWidth(0.78f), shape = Shapes.small
    ) {

        Text(text = text, color = textColor)
    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun IntroScreenPreview() {
    MainAppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = BackgroundMain
        ) {
            IntroScreen({}, {})
        }

    }
}