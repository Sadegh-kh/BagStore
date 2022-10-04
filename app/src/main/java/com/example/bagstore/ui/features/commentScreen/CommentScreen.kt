@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.bagstore.ui.features.commentScreen


import android.annotation.SuppressLint
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun CommentScreen(productId: String) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            modifier = Modifier.shadow(5.dp),
            title = {
                Text(text = "Comments", fontSize = 20.sp)
            },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Default.ArrowBackIosNew, contentDescription = "onBack")

                }
            }

        )
    }) {


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Preview() {
    CommentScreen("2")
}