package com.example.bagstore.ui.features.singInScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bagstore.R
import com.example.bagstore.ui.theme.BackgroundMain
import com.example.bagstore.ui.theme.Blue
import com.example.bagstore.ui.theme.Shapes
import com.example.bagstore.util.MyScreens
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel


@Composable
fun SingInScreen() {

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(Blue)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.9f)
        ) {
            ShapeImage()
            CardViewSingUp()
        }
    }


}

@Composable
fun TextFieldInfo(
    hint: String, icon: Int, text: String, textFieldChange: (String) -> Unit
) {

    OutlinedTextField(
        label = { Text(text = hint) },
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon), contentDescription = null
            )
        },
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        value = text,
        onValueChange = textFieldChange,
        shape = Shapes.medium,
    )
}

@Composable
fun TextFieldPassword(
    hint: String, icon: Int, text: String, textFieldChange: (String) -> Unit
) {

    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    OutlinedTextField(
        label = { Text(text = hint) },
        singleLine = true,
        leadingIcon = {
            Icon(
                painter = painterResource(id = icon), contentDescription = null
            )
        },
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        value = text,
        onValueChange = textFieldChange,
        shape = Shapes.medium,
        visualTransformation =
        if (passwordVisibility.value) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        trailingIcon = {
            val imageIcon = if (passwordVisibility.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }
            val description = if (passwordVisibility.value) "Hide Password" else "Show Password"
            IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                Icon(imageVector = imageIcon, contentDescription = description)

            }
        }
    )
}

@Composable
fun ColumnInfo(viewModel: SingInViewModel) {
    //states from view model
    val emailState = viewModel.emailState.observeAsState(initial = "")
    val passwordState = viewModel.passwordState.observeAsState(initial = "")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth(0.92f)
            .padding(horizontal = 10.dp)
    ) {
        TextFieldInfo(
            hint = "Email",
            icon = R.drawable.ic_email,
            text = emailState.value,
            textFieldChange = { viewModel.emailState.value = it })
        TextFieldPassword(
            hint = "Password",
            icon = R.drawable.ic_password,
            text = passwordState.value,
            textFieldChange = { viewModel.passwordState.value = it })
    }
}

@Composable
fun CardViewSingUp() {
    //injection
    val navigation = getNavController()
    val viewModel = getNavViewModel<SingInViewModel>()

    Card(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(0.9f),
        shape = Shapes.small,
        elevation = 6.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.wrapContentSize()
        ) {

            Text(
                text = "Sing Up",
                color = Blue,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 18.dp, bottom = 24.dp)
            )

            ColumnInfo(viewModel)

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .padding(top = 14.dp, bottom = 7.dp),
                shape = Shapes.small
            ) {

                Text(text = "Register Account", modifier = Modifier.padding(10.dp))

            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(bottom = 10.dp)
            ) {
                Text(text = "Don't have any account?", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = {
                    navigation.navigate(MyScreens.SingUpScreen.route){
                        //transfer to IntroScreen when click on BackButton
                        popUpTo(MyScreens.SingInScreen.route){
                            inclusive=true
                        }
                    }
                }) {
                    Text(text = "Register Now", color = Blue, fontSize = 15.sp)
                }
            }

        }

    }
}

@Composable
fun ShapeImage() {

    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(CircleShape)
            .background(BackgroundMain), contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_icon_app),
            contentDescription = null,
            modifier = Modifier
                .padding(10.dp)
                .size(40.dp)
        )

    }


}

@Preview(showBackground = true)
@Composable
fun SingUpScreenPreview() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopCenter) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.4f)
                .background(Blue)
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
        ) {
            ShapeImage()
            CardViewSingUp()
        }
    }

}