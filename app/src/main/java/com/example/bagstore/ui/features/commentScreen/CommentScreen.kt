@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.example.bagstore.ui.features.commentScreen


import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bagstore.model.data.Comment
import com.example.bagstore.ui.features.productScreen.CommentItem

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@ExperimentalMaterial3Api
@Composable
fun CommentScreen(productId: String) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.shadow(5.dp),
                title = {
                    Text(text = "Comments", fontSize = 20.sp)
                },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "onBack"
                        )

                    }
                },
                scrollBehavior = scrollBehavior,
                //color when scrolled
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    scrolledContainerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(
                        0.dp
                    )
                )

            )
        }) {

        LazyColumn(modifier = Modifier.padding(horizontal = 10.dp)){
            item {
                Spacer(modifier = Modifier.padding(top = 80.dp))
            }
            items(10){
                CommentItem(comment = Comment("2","Very Good","SadeghKHoshbaya@gmail.com"))
            }
        }


    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun Preview() {
    CommentScreen("2")
}