package com.example.bagstore.ui.features.productScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bagstore.ui.theme.BackgroundMain
import com.example.bagstore.R
import com.example.bagstore.ui.theme.Shapes

@Composable
fun ProductScreen(productId: String) {
    Column(modifier = Modifier.verticalScroll(state = rememberScrollState())) {
        TopAppBar(backgroundColor = BackgroundMain, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "onBack"
                )
            }
        }, title = {
            Text(
                text = "Details",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
            )
        }, actions = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "onShoppingCard"
                )
            }
        })

        Spacer(modifier = Modifier.padding(top = 15.dp))

        ProductImage(this)
        ProductDescription()
    }

}

@Composable
fun ProductDescription() {
    Column(modifier = Modifier.padding(10.dp)) {
        Text(text = "Mahi", style = MaterialTheme.typography.h6, fontSize = 20.sp)

        Spacer(modifier = Modifier.padding(top = 10.dp))

        Text(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla ornare mi porta lacus tempor elementum. Vestibulum hendrerit ipsum ac enim rutrum, quis sagittis diam scelerisque. Aenean consequat, enim nec convallis volutpat, magna arcu dapibus risus, non aliquam nulla mi a augue. Maecenas in enim pharetra, placerat orci ac, volutpat urna. Quisque luctus facilisis urna auctor bibendum. Ut ac neque tortor. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse lobortis fermentum dapibus. Ut tempus sodales libero ut pellentesque. Aliquam pellentesque accumsan scelerisque. Duis consectetur tortor augue, at porttitor odio elementum interdum.",
            textAlign = TextAlign.Justify
        )


    }
}

@Composable
fun ProductImage(columnScope: ColumnScope) {
    columnScope.apply {
        Image(
            painter = painterResource(id = R.drawable.img_intro),
            contentDescription = "ProductImage",
            modifier = Modifier
                .fillMaxWidth(0.95f)
                .height(250.dp)
                .clip(Shapes.medium)
                .align(Alignment.CenterHorizontally),
            contentScale = ContentScale.Crop
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    ProductScreen(productId = "2")
}