package com.example.bagstore.ui.features.categoryScreen


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bagstore.R
import com.example.bagstore.ui.theme.BackgroundMain
import com.example.bagstore.ui.theme.Blue
import com.example.bagstore.ui.theme.Shapes

@Composable
fun CategoryScreen(categoryName: String) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundMain), horizontalAlignment = Alignment.CenterHorizontally
    ) {

        TopAppBar(
            modifier = Modifier
                .wrapContentHeight()
                .padding(bottom = 10.dp, top = 10.dp),
            title = {
                Text(
                    text = categoryName.replaceFirstChar {
                        it.uppercase()
                    }, modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 22.sp
                )
            }, backgroundColor = BackgroundMain,
            elevation = 0.dp
        )

        Product()
    }

}

@Composable
fun Product() {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.92f)
            .height(300.dp),
        elevation = 4.dp
    ) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.img_intro),
                contentDescription = "productImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxHeight(0.76f)
            )
            ProductInfo()

        }
    }
}

@Composable
fun ProductInfo() {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
        , modifier = Modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(10.dp)
        ) {
            Text(
                text = "Roosevelt Buffalo",
                style = MaterialTheme.typography.h6,
                fontSize = 18.sp
            )

            Text(
                text = "820 Tomans",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
        Box(contentAlignment = Alignment.BottomEnd
            , modifier = Modifier.align(Alignment.Bottom)
                .padding(10.dp)
                .clip(Shapes.medium)) {
            Text(text = "42 Sold",modifier = Modifier
                .background(Blue)
                .padding(8.dp), color = Color.White)

        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CategoryScreen("backpack")
}