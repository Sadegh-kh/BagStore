package com.example.bagstore.ui.features.productScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bagstore.ui.features.mainScreen.TopToolBar
import com.example.bagstore.ui.theme.BackgroundMain
import com.example.bagstore.ui.theme.MainAppTheme

@Composable
fun ProductScreen(productId: String) {
    TopAppBar(backgroundColor = BackgroundMain
        , navigationIcon = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ArrowBack,
                contentDescription = "onBack")
        }
    }
        , title = {
        Text(
            text = "Details",
            modifier = Modifier.fillMaxWidth().padding(end = 20.dp),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,)
    }
        , actions = {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.ShoppingCart,
                contentDescription = "onShoppingCard")
        }
    })


}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    ProductScreen(productId = "2")
}