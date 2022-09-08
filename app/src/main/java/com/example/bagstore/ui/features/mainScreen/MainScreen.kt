package com.example.bagstore.ui.features.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bagstore.R
import com.example.bagstore.ui.theme.BackgroundBlue
import com.example.bagstore.ui.theme.BackgroundMain
import com.example.bagstore.ui.theme.MainAppTheme
import com.example.bagstore.ui.theme.Shapes


@Composable
fun MainScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        TopToolBar()
        CategoryList()
    }

}

@Composable
fun CategoryList() {
    LazyRow(modifier = Modifier.padding(top = 16.dp), contentPadding = PaddingValues(end = 18.dp)) {
        items(10) {
            CategoryItem()
        }

    }
}

@Composable
fun CategoryItem() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(start = 18.dp)
    ) {
        Surface(shape = Shapes.small) {

            Image(
                painter = painterResource(id = R.drawable.ic_icon_app),
                contentDescription = "categoryItem",
                modifier = Modifier
                    .background(BackgroundBlue)
                    .clickable {  }
                    .padding(18.dp)
            )

        }

        Text(text = "Hotels", modifier = Modifier.padding(top = 5.dp), color = Color.Gray)

    }
}

@Composable
fun TopToolBar() {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = BackgroundMain,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Top),
        title = { Text(text = "Bag Store") },
        actions = {
            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)

            }

            IconButton(onClick = {}) {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)

            }
        })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}
