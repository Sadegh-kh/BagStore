package com.example.bagstore.ui.features.mainScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

        ProductSubject()
    }

}

@Composable
fun ProductSubject() {
    Column {
        Text(
            text = "Popular Destinations",
            style = MaterialTheme.typography.h6,
            fontSize = 25.sp,
            modifier = Modifier.padding(top = 30.dp, bottom = 20.dp, start = 20.dp)
        )
        LazyRow(contentPadding = PaddingValues(end = 20.dp)) {
            items(10) {
                ProductItem()
            }
        }

    }
}

@Composable
fun ProductItem() {
    Card(modifier = Modifier
        .wrapContentSize()
        .padding(start = 20.dp)
        .clickable {  },
        shape = Shapes.medium) {
        Column(modifier = Modifier.wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(id = R.drawable.img_intro),
                contentDescription = "Product Subject Image",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth().size(250.dp)
            )

            ProductTextBox()

        }


    }
}

@Composable
fun ProductTextBox() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(250.dp,100.dp)
            .background(Color.White), contentAlignment = Alignment.TopStart
    ) {
        Column(Modifier.padding(10.dp)) {
            Text(
                text = "Diamond Woman Watches",
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 5.dp),
                fontSize = 18.sp,
                maxLines = 1
            )

            Text(text = "86,000 Tomans", fontSize = 17.sp, color = Color.DarkGray)

            Text(text = "156 sold", fontSize = 15.sp, color = Color.Gray)
        }

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
                    .clickable { }
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
