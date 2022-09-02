package com.example.bagstore.ui.features.singUpScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Error
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
import com.example.bagstore.ui.theme.ErrorIcon
import com.example.bagstore.ui.theme.Shapes
import com.example.bagstore.util.MyScreens
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel


@Composable
fun SingUpScreen() {
/*
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()

    ) {
        TextFieldInformation(
            hint = "Enter your Email",
            icon = R.drawable.ic_email,
            text = ""
        ) {
        }
    }*/

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
fun CardViewSingUp() {
    //injection
    val navigation = getNavController()
    val viewModel = getNavViewModel<SingUpViewModel>()

    viewModel.resetStates()

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
                onClick = {
                    checkFields(viewModel)
                },
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
                Text(text = "Already have an account?", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = {
                    navigation.navigate(MyScreens.SingInScreen.route) {
                        //transfer to IntroScreen when click on BackButton
                        popUpTo(MyScreens.SingUpScreen.route) {
                            inclusive = true
                        }
                    }
                }) {
                    Text(text = "Log In", color = Blue, fontSize = 15.sp)
                }
            }

        }

    }
}

@Composable
fun ColumnInfo(viewModel: SingUpViewModel) {
    //states from view model
    val nameState = viewModel.nameState.observeAsState(initial = "")
    val emailState = viewModel.emailState.observeAsState(initial = "")
    val passwordState = viewModel.passwordState.observeAsState(initial = "")
    val configPasswordState = viewModel.configPasswordState.observeAsState(initial = "")

    val errorStateForName = viewModel.errorStateForName.observeAsState(false)
    val errorStateForEmail = viewModel.errorStateForEmail.observeAsState(false)
    val errorStateForPassword = viewModel.errorStateForPassword.observeAsState(false)
    val errorStateForConfigPassword = viewModel.errorStateForConfigPassword.observeAsState(false)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier
            .fillMaxWidth(0.92f)
            .padding(horizontal = 10.dp)
    ) {
        TextFieldInfo(
            hint = "Your Full Name",
            icon = R.drawable.ic_person,
            text = nameState.value,
            textFieldChange = {
                viewModel.nameState.value = it
                viewModel.errorStateForName.value = false
            },
            error = errorStateForName.value
        )
        TextFieldInfo(
            hint = "Email",
            icon = R.drawable.ic_email,
            text = emailState.value,
            textFieldChange = {
                viewModel.emailState.value = it
                viewModel.errorStateForEmail.value = false
            },
            error = errorStateForEmail.value
        )
        TextFieldPassword(
            hint = "Password",
            icon = R.drawable.ic_password,
            text = passwordState.value,
            textFieldChange = {
                viewModel.passwordState.value = it
                viewModel.errorStateForPassword.value = false
            },
            error = errorStateForPassword.value
        )
        TextFieldPassword(
            hint = "Confirm Password",
            icon = R.drawable.ic_password,
            text = configPasswordState.value,
            textFieldChange = {
                viewModel.configPasswordState.value = it
                viewModel.errorStateForConfigPassword.value = false
            },
            error = errorStateForConfigPassword.value
        )
    }
}


@Composable
fun TextFieldInfo(
    hint: String, error: Boolean, icon: Int, text: String, textFieldChange: (String) -> Unit
) {
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        OutlinedTextField(
            label = {
                val colorTextError = if (error) {
                    MaterialTheme.colors.error
                } else {
                    LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                }
                Text(text = hint, color = colorTextError)
            },
            singleLine = true,
            leadingIcon = {
                val tintIcon = if (error) {
                    ErrorIcon
                } else {
                    LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                }
                Icon(
                    painter = painterResource(id = icon), contentDescription = null, tint = tintIcon
                )
            },
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            value = text,
            onValueChange = textFieldChange,
            shape = Shapes.medium,
            isError = error,
            trailingIcon = {
                if (error) {
                    Icon(
                        imageVector = Icons.Filled.Error,
                        contentDescription = "Error",
                        tint = MaterialTheme.colors.error
                    )
                }
            }
        )
        if (error) {
            val textCheng = if (icon == R.drawable.ic_email) {
                "Email Address"
            } else {
                "Name"
            }
            Text(
                text = "Write your $textCheng !!",
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp, top = 2.dp)
            )
        }
    }

}

@Composable
fun TextFieldPassword(
    hint: String, error: Boolean, icon: Int, text: String, textFieldChange: (String) -> Unit
) {
    val passwordVisibility = rememberSaveable { mutableStateOf(false) }
    Column(modifier = Modifier.padding(bottom = 10.dp)) {
        OutlinedTextField(
            value = text,
            onValueChange = textFieldChange,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            shape = Shapes.medium,
            label = {
                val colorTextError = if (error) {
                    MaterialTheme.colors.error
                } else {
                    LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                }
                Text(text = hint, color = colorTextError)
            },
            singleLine = true,
            isError = error,
            leadingIcon = {
                val tintIcon = if (error) {
                    ErrorIcon
                } else {
                    LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                }
                Icon(
                    painter = painterResource(id = icon), contentDescription = null, tint = tintIcon
                )
            },

            //for password visibility
            visualTransformation =
            if (passwordVisibility.value) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            trailingIcon = {

                //if visibility true show your password
                val imageIcon = if (passwordVisibility.value) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }

                //icon tint for error
                val tintIcon = if (error) {
                    ErrorIcon
                } else {
                    LocalContentColor.current.copy(alpha = LocalContentAlpha.current)
                }
                val description = if (passwordVisibility.value) "Hide Password" else "Show Password"
                IconButton(onClick = { passwordVisibility.value = !passwordVisibility.value }) {
                    Icon(imageVector = imageIcon, contentDescription = description, tint = tintIcon)

                }
            }
        )
        if (error) {
            val textCheck = if (error && text.isNotEmpty()) {
                "Your Password doesn't same !!"
            } else {
                "Write Your $hint !!"
            }
            Text(
                text = textCheck,
                color = MaterialTheme.colors.error,
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 16.dp, top = 2.dp)
            )
        }
    }

}

@Composable
fun ShapeImage() {

    Box(
        modifier = Modifier
            .wrapContentSize()
            .padding(bottom = 8.dp, top = 8.dp)
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

@Composable
fun BackgroundSingUp() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

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


fun checkFields(viewModel: SingUpViewModel) {
    //when you don't write anything and click on button show an error
    if (viewModel.nameState.value!!.isEmpty()) {
        viewModel.errorStateForName.value = true
    }
    if (viewModel.emailState.value!!.isEmpty()) {
        viewModel.errorStateForEmail.value = true
    }
    if (viewModel.passwordState.value!!.isEmpty()) {
        viewModel.errorStateForPassword.value = true
    }

    if (viewModel.configPasswordState.value!!.isEmpty()) {
        viewModel.errorStateForConfigPassword.value = true
    } else {
        //check to same password
        if (viewModel.passwordState.value != viewModel.configPasswordState.value) {
            viewModel.errorStateForConfigPassword.value = true
        }
    }
}


