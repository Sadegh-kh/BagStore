package com.example.bagstore.ui.features.productScreen

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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*
import com.example.bagstore.R
import com.example.bagstore.ui.theme.BackgroundMain
import com.example.bagstore.ui.theme.Blue
import com.example.bagstore.ui.theme.Shapes
import com.example.bagstore.ui.theme.tagShape
import androidx.compose.material3.BottomAppBar as BottomAppBar

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

    Scaffold(

        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            Surface(elevation = 5.dp) {
                CenterAlignedTopAppBar(
                    navigationIcon = {
                        IconButton(onClick = { /*TODO*/ }) {
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
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(
                                imageVector = Icons.Default.ShoppingCart,
                                contentDescription = "onShoppingCard"
                            )
                        }
                    },
                    scrollBehavior = scrollBehavior,
                    colors = topAppBarBackgroundColor
                )
            }

        },

        bottomBar = {
            BottomAppBar(
                elevation = 20.dp, backgroundColor = BackgroundMain
                        , contentPadding = PaddingValues(10.dp)
            ) {
                    Text(
                        text = "Add To Card",
                        modifier = Modifier
                            .clip(Shapes.medium)
                            .background(Blue)
                            .padding(10.dp),
                        color = Color.White
                    )

                Spacer(modifier = Modifier.weight(1f))

                Text(text = "100 Tomans")
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

                ProductImage()
                ProductDescription()

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
        Spacer(modifier = Modifier.padding(bottom = 60.dp))
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
fun ProductDescription() {
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        Text(text = "Mahi", style = MaterialTheme.typography.h6, fontSize = 20.sp)

        Spacer(modifier = Modifier.padding(top = 10.dp))

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla ornare mi porta lacus tempor elementum. Vestibulum hendrerit ipsum ac enim rutrum, quis sagittis diam scelerisque. Aenean consequat, enim nec convallis volutpat, magna arcu dapibus risus, non aliquam nulla mi a augue. Maecenas in enim pharetra, placerat orci ac, volutpat urna. Quisque luctus facilisis urna auctor bibendum. Ut ac neque tortor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse lobortis fermentum dapibus. Ut tempus sodales libero ut pellentesque. Aliquam pellentesque accumsan scelerisque. Duis consectetur tortor augue, at porttitor odio elementum interdum.",
            textAlign = TextAlign.Justify,
            lineHeight = 25.sp
        )

        TextButton(onClick = { /*TODO*/ }, modifier = Modifier.padding(10.dp)) {
            Text(text = "#Clutch")
        }
    }
}

@Composable
fun ProductImage() {
    Image(
        painter = painterResource(id = R.drawable.img_intro),
        contentDescription = "ProductImage",
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(Shapes.medium),
        contentScale = ContentScale.Crop
    )
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
}