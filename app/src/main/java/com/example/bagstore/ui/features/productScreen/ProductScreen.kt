package com.example.bagstore.ui.features.productScreen

import androidx.activity.findViewTreeOnBackPressedDispatcherOwner
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import coil.compose.AsyncImage
import com.example.bagstore.R
import com.example.bagstore.model.data.Product
import com.example.bagstore.ui.theme.*
import com.example.bagstore.util.MyScreens
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.viewmodel.getViewModel
import org.koin.core.parameter.parametersOf

@ExperimentalMaterial3Api
@Composable
fun ProductScreen(productId: String) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    //set background color when scrolled
    val topAppBarBackgroundColor = TopAppBarDefaults.centerAlignedTopAppBarColors(
        scrolledContainerColor = androidx.compose.material3.MaterialTheme.colorScheme.surfaceColorAtElevation(
            0.dp
        )
    )

    val onBackPressed = LocalView.current.findViewTreeOnBackPressedDispatcherOwner()

    val navController = getNavController()

    val viewModel= getViewModel<ProductScreenViewModel>(
        parameters = {parametersOf(productId)}

    )

    Scaffold(

        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
                CenterAlignedTopAppBar(
                    modifier = Modifier.shadow(5.dp),
                    navigationIcon = {
                        IconButton(onClick = { onBackPressed!!.onBackPressedDispatcher.onBackPressed() }) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "onBack"
                            )
                        }
                    },

                    title = {
                        Text(
                            text = "Details",
                            fontSize = 20.sp,
                        )
                    },

                    actions = {
                        IconButton(onClick = {
                            navController.navigate(MyScreens.ShoppingCardScreen.route)
                        }) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "onShoppingCard"
                            )
                        }
                    },

                    scrollBehavior = scrollBehavior,
                    colors = topAppBarBackgroundColor
                )
        },

        bottomBar = {
            BottomAppBar(
                elevation = 20.dp,
                backgroundColor = BackgroundMain,
                contentPadding = PaddingValues(5.dp)
            ) {
                Text(
                    text = "Add Product To Card",
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .clip(Shapes.medium)
                        .background(Blue)
                        .padding(10.dp),
                    color = Color.White
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "100 Tomans",
                    modifier = Modifier
                        .padding(end = 5.dp)
                        .clip(Shapes.medium)
                        .background(Blue.copy(alpha = 0.04f))
                        .padding(5.dp)
                )
            }

            /*Column {

                Spacer(
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(0.1f),
                                )
                            )
                        )
                        .fillMaxWidth()
                        .height(3.dp)

                )

                Row(
                    modifier = Modifier
                        .background(BackgroundMain)
                        .padding(10.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                }
            }*/
        }) {
        it
        LazyColumn(modifier = Modifier.padding(horizontal = 10.dp)) {
            item {

                Spacer(modifier = Modifier.padding(top = 80.dp))

                ProductImage(viewModel.productState.value)
                ProductDescription(viewModel.productState.value)

                Divider()

                ProductDetails()

                Divider()

                productComments(this@LazyColumn)
            }
        }
    }
}


fun productComments(lazyListScope: LazyListScope) {
    lazyListScope.item {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Comments", fontSize = 20.sp, style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.weight(1f))

            TextButton(onClick = {}, modifier = Modifier.align(Alignment.Top)) {
                Text(text = "Add New Comment", fontSize = 15.sp)
            }
        }
    }

    lazyListScope.items(3) {
        CommentItem()
    }

    lazyListScope.item {
        MoreComment()
        Spacer(modifier = Modifier.padding(bottom = 70.dp))
    }

}

@Composable
fun MoreComment() {
    Box(
        modifier = Modifier
            .fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        Column(modifier = Modifier
            .clip(CircleShape)
            .clickable(
                interactionSource = MutableInteractionSource(),
                indication = rememberRipple(color = Color.White)
            ) {

            }
            .padding(10.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.ic_more),
                contentDescription = "MoreComment",
                modifier = Modifier
                    .border(width = 2.dp, color = Color.Black, shape = CircleShape)
                    .size(40.dp)
            )
            Text(text = "More", fontSize = 15.sp)
        }

    }
}

@Composable
fun CommentItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(bottom = 10.dp)
            .border(
                border = BorderStroke(width = 1.dp, color = Color.LightGray),
                shape = Shapes.medium
            )
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Text(
                text = "SadeghKhoshbayan1@gmail.com",
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "Alie Bud!!     sa",
                textAlign = TextAlign.Justify,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

@Composable
fun ProductDetails() {
    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_details_comment),
                    contentDescription = "commentDetail"
                )
                Text(
                    text = "6 Comments",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_details_material),
                    contentDescription = "materialDetail"
                )
                Text(
                    text = "Leather",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_details_sold),
                    contentDescription = "soldDetail"
                )
                Text(
                    text = "50 Sold",
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

        }

        Spacer(modifier = Modifier.weight(1f))

        Box(
            contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .align(Alignment.Bottom)
                .clip(tagShape)
        ) {

            Text(
                text = "Best Sellers",
                Modifier
                    .background(Blue)
                    .padding(10.dp)
                    .padding(start = 15.dp),
                fontSize = 12.sp,
                color = Color.White
            )

        }
    }
}

@Composable
fun ProductDescription(product: Product) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        Text(text = product.name, style = MaterialTheme.typography.h6, fontSize = 20.sp)

        Spacer(modifier = Modifier.padding(top = 10.dp))

        Text(
            text=product.detailText,
            textAlign = TextAlign.Justify,
            lineHeight = 25.sp
        )

        TextButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp)) {
            Text(text = product.category)
        }
    }
}

@Composable
fun ProductImage(product: Product) {
    AsyncImage(model = product.imgUrl ,contentDescription = "ProductImage",
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(Shapes.medium),
        contentScale = ContentScale.Crop)
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProductScreen(productId = "2")
}