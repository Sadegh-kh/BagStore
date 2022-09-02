package com.example.bagstore.ui.features.singInScreen

import android.util.Log
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
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.example.bagstore.R
import com.example.bagstore.ui.theme.*
import com.example.bagstore.util.MyScreens
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.navigation.getNavViewModel
import okio.Closeable
import java.nio.channels.AsynchronousChannel

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
fun CardViewSingUp() {
    //injection
    val navigation = getNavController()
    val viewModel = getNavViewModel<SingInViewModel>()
    val context = LocalContext.current

    //when you comeback to this screen ,it clear fields
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
                    //when you don't write anything and click on button show an error
                    if (viewModel.emailState.value!!.isEmpty()) {
                        viewModel.errorStateForEmail.value = true
                    }
                    if (viewModel.passwordState.value!!.isEmpty()) {
                        viewModel.errorStateForPassword.value = true
                    }

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
                Text(text = "Don't have any account?", fontSize = 16.sp)
                Spacer(modifier = Modifier.width(4.dp))
                TextButton(onClick = {
                    navigation.navigate(MyScreens.SingUpScreen.route) {

                        //transfer to IntroScreen when click on BackPressed
                        popUpTo(MyScreens.SingInScreen.route) {
                            inclusive = true
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
fun ColumnInfo(viewModel: SingInViewModel) {
    //states from view model
    val emailState = viewModel.emailState.observeAsState(initial = "")
    val passwordState = viewModel.passwordState.observeAsState(initial = "")

    val emailErrorState = viewModel.errorStateForEmail.observeAsState(initial = false)
    val passwordErrorState = viewModel.errorStateForPassword.observeAsState(initial = false)

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
            textFieldChange = {
                viewModel.emailState.value = it
                viewModel.errorStateForEmail.value = false
            },
            error = emailErrorState.value
        )

        TextFieldPassword(
            hint = "Password",
            icon = R.drawable.ic_password,
            text = passwordState.value,
            textFieldChange = {
                viewModel.passwordState.value = it
                viewModel.errorStateForPassword.value = false
            },
            error = passwordErrorState.value
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
            Text(
                text = "Write your Email Address!!",
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
            Text(
                text = "Write your Password!!",
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