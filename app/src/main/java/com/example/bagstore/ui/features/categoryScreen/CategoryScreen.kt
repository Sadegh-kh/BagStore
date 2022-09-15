package com.example.bagstore.ui.features.categoryScreen


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bagstore.model.data.Product
import com.example.bagstore.ui.theme.BackgroundMain
import com.example.bagstore.ui.theme.Blue
import com.example.bagstore.ui.theme.Shapes
import dev.burnoo.cokoin.viewmodel.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun CategoryScreen(categoryName: String) {
    val viewModel= getViewModel<CategoryScreenViewModel>(
        parameters = {
            parametersOf(categoryName)
        }
    )

    val productList=viewModel.categoryProductState.value
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundMain)
        , horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(1){
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
        }

        items(productList.size){
            Product(productList[it])
        }

    }

}

@Composable
fun Product(product: Product) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.92f)
            .height(300.dp)
            .padding(bottom = 20.dp)
            .clickable { },
        elevation = 4.dp
    ) {
        Column {
            AsyncImage(model = product.imgUrl,
                contentDescription = "productImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxHeight(0.76f) )

            ProductInfo(product)

        }
    }
}

@Composable
fun ProductInfo(product: Product) {
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
                text = product.name,
                style = MaterialTheme.typography.h6,
                fontSize = 18.sp
            )

            Text(
                text = product.price + " Tomans",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
        Box(modifier = Modifier
            .align(Alignment.Bottom)
            .padding(10.dp)
            .clip(Shapes.medium)) {

            Text(text = product.soldItem +" Sold"
                ,modifier = Modifier
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