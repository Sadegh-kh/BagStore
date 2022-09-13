package com.example.bagstore.ui.features.mainScreen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bagstore.model.data.Advertisement
import com.example.bagstore.model.data.Product
import com.example.bagstore.ui.theme.*
import com.example.bagstore.util.CATEGORY
import com.example.bagstore.util.MyScreens
import com.example.bagstore.util.NetworkChecker
import com.example.bagstore.util.TAGS
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.viewmodel.getViewModel
import org.koin.core.parameter.parametersOf


@Composable
fun MainScreen() {
    val context = LocalContext.current
    val viewModel = getViewModel<MainScreenViewModel>(
        parameters = {
            //set net condition for MainScreenViewModel from modules(di)
            parametersOf(NetworkChecker(context).isInternetConnected)
        }
    )
    val navController= getNavController()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(bottom = 25.dp)
    ) {
        item {
            val progressBarVisibility = viewModel.showProgressBar.value
            val productList = viewModel.productState.value
            val advertisementList = viewModel.advertisementState.value
            if (progressBarVisibility) {
                LinearProgressIndicator(
                    modifier = Modifier.fillMaxWidth(),
                    color = Blue
                )
            }

            TopToolBar(onClickShoppingCart = {
                navController.navigate(MyScreens.ShoppingCardScreen.route)
            },
            onProfileClicked = {
                navController.navigate(MyScreens.ProfileScreen.route)
            })

            CategoryBar(CATEGORY, onCategoryClicked = {
                navController.navigate(MyScreens.CategoryScreen.route +"/"+ it)
            })

            if (!progressBarVisibility) {

                BunchOfProduct(TAGS, productList, advertisementList){
                    navController.navigate(MyScreens.ProductScreen.route+"/"+it)
                }

            }

        }
    }

}

@Composable
fun BunchOfProduct(
    tags: List<String>,
    productList: List<Product>,
    advertisementList: List<Advertisement>,onProductClicked:(String)->Unit
) {
    //random number for show a advertisement to different place
    val randomNumber = (0..3).shuffled().last()
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        tags.forEachIndexed { tagIndex, tag ->

            val productFilterByTag = productList.filter { product ->
                product.tags == tag
            }

            ProductBar(tag, productFilterByTag.shuffled(),onProductClicked)

            if (advertisementList.isNotEmpty()){
                if (randomNumber == tagIndex) {
                    AdvertisementBanner(advertisementList)
                }
            }

        }


    }
}

@Composable
fun AdvertisementBanner(advertisementList: List<Advertisement>) {
    //show random advertisement
    val randomIndex = (0..1).shuffled().last()
    AsyncImage(model = advertisementList[randomIndex].imageURL,
        contentDescription = "advertisement",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth(0.9f)
            .height(250.dp)
            .padding(top = 18.dp)
            .clip(Shapes.medium)
            .clickable { } )
}

@Composable
fun ProductBar(tag: String, productFilterByTag: List<Product>,onProductClicked:(String)->Unit) {
    Column {
        val context = LocalContext.current
        var visibilityTag = ""
        if (productFilterByTag.isEmpty()) {
            Toast.makeText(context, "please connect to the internet...", Toast.LENGTH_SHORT).show()
        } else {
            visibilityTag = tag
        }

        Text(
            text = visibilityTag,
            style = MaterialTheme.typography.h6,
            fontSize = 25.sp,
            modifier = Modifier.padding(top = 30.dp, bottom = 20.dp, start = 20.dp)
        )
        LazyRow(contentPadding = PaddingValues(end = 20.dp)) {
            items(productFilterByTag.size) {
                ProductItem(productFilterByTag[it],onProductClicked)
            }
        }

    }
}

@Composable
fun ProductItem(product: Product,onProductClicked:(String)->Unit) {
    Card(
        modifier = Modifier
            .wrapContentSize()
            .padding(start = 20.dp)
            .clickable { onProductClicked.invoke(product.productId) },
        shape = Shapes.medium
    ) {
        Column(
            modifier = Modifier.wrapContentSize()
        ) {
            AsyncImage(
                model = product.imgUrl, contentDescription = "Product Subject Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(250.dp)
            )

            ProductTextBox(product)

        }


    }
}

@Composable
fun ProductTextBox(product: Product) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .size(250.dp, 100.dp)
            .background(Color.White), contentAlignment = Alignment.TopStart
    ) {
        Column(Modifier.padding(10.dp)) {
            Text(
                text = product.name,
                style = MaterialTheme.typography.h5,
                modifier = Modifier.padding(bottom = 5.dp),
                fontSize = 18.sp,
                maxLines = 1
            )

            Text(text = product.price + " Tomans ", fontSize = 17.sp, color = Color.DarkGray)

            Text(
                text = product.soldItem + " Sold",
                fontSize = 15.sp,
                color = Color.Gray,
                modifier = Modifier.padding(top = 2.dp)
            )
        }

    }
}

@Composable
fun CategoryBar(category: List<Pair<String, Int>>, onCategoryClicked: (String) -> Unit) {
    LazyRow(modifier = Modifier.padding(top = 16.dp), contentPadding = PaddingValues(end = 18.dp)) {
        items(category.size) {
            CategoryItem(category[it],onCategoryClicked)
        }

    }
}

@Composable
fun CategoryItem(categoryItem: Pair<String, Int>, onCategoryClicked: (String) -> Unit) {
    Column(
        modifier = Modifier.padding(start = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Surface(shape = Shapes.small) {

            Image(
                painter = painterResource(id = categoryItem.second),
                contentDescription = "categoryItem",
                modifier = Modifier
                    .background(BackgroundBlue)
                    .clickable{ onCategoryClicked.invoke(categoryItem.first)}
                    .padding(18.dp)
            )

        }

        Text(text = categoryItem.first, modifier = Modifier.padding(top = 5.dp), color = Color.Gray)

    }
}

@Composable
fun TopToolBar(onClickShoppingCart:()->Unit, onProfileClicked:()->Unit) {
    TopAppBar(
        elevation = 0.dp,
        backgroundColor = BackgroundMain,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.Top),
        title = { Text(text = "Bag Store") },
        actions = {
            IconButton(onClick = {
                onClickShoppingCart.invoke()
            }) {
                Icon(imageVector = Icons.Default.ShoppingCart, contentDescription = null)

            }

            IconButton(onClick = {
                onProfileClicked.invoke()
            }) {
                Icon(imageVector = Icons.Default.Person, contentDescription = null)

            }
        })
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainScreen()
}
