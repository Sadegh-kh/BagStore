package com.example.bagstore.ui.features.productScreen

import android.util.Log
import android.widget.Toast
import androidx.activity.findViewTreeOnBackPressedDispatcherOwner
import androidx.compose.foundation.*
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.*
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
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import com.example.bagstore.R
import com.example.bagstore.model.data.Product
import com.example.bagstore.ui.theme.*
import com.example.bagstore.util.MyScreens
import dev.burnoo.cokoin.navigation.getNavController
import dev.burnoo.cokoin.viewmodel.getViewModel
import org.koin.core.parameter.parametersOf
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.example.bagstore.model.data.Comment

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

    val viewModel = getViewModel<ProductScreenViewModel>(
        parameters = { parametersOf(productId) }

    )

    val context = LocalContext.current

    Scaffold(

        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                modifier = Modifier.shadow(5.dp),
                navigationIcon = {
                    IconButton(onClick = {
                        navController.navigate(MyScreens.MainScreen.route) {
                            popUpTo(MyScreens.MainScreen.route) {
                                inclusive = true
                            }
                        }
                        /*onBackPressed!!.onBackPressedDispatcher.onBackPressed()*/
                    }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "onBackToMainScreen"
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

        }) {
        it
        Column(
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.padding(top = 80.dp))

            ProductImage(viewModel.productState.value)
            ProductDescription(viewModel.productState.value, navController)

            Divider()

            ProductDetails(viewModel.productState.value, viewModel.commentListState.value)

            Divider()

            ProductComments(viewModel, navController,
                addNewCommentClicked = {
                    viewModel.addNewComment(productId = productId, viewModel.textCommentState.value,
                        resultMessage = {
                            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                        })
                })
        }

    }
}

@ExperimentalMaterial3Api
@Composable
fun ProductComments(
    viewModel: ProductScreenViewModel,
    navController: NavHostController,
    addNewCommentClicked: () -> Unit
) {

    //add new comment dialog
    val dialogVisibility = viewModel.dialogVisibilityState
    //dialog
    if (dialogVisibility.value) {
        Dialog(
            onDismissRequest = { dialogVisibility.value = false },
            properties = DialogProperties(dismissOnClickOutside = false)
        ) {

            Column(
                modifier = Modifier
                    .clip(Shapes.small)
                    .background(Color.White)
                    .fillMaxHeight(0.55f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Write Your Comment",
                    modifier = Modifier.padding(vertical = 15.dp),
                    fontSize = 20.sp,
                    style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                )
                OutlinedTextField(value = viewModel.textCommentState.value,
                    onValueChange = { viewModel.textCommentState.value = it },
                    modifier = Modifier
                        .fillMaxWidth(0.9f)
                        .padding(top = 8.dp),
                    label = {
                        Text(text = "Write Something")
                    })

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .padding(end = 5.dp), horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { dialogVisibility.value = false }) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = { addNewCommentClicked.invoke()
                    dialogVisibility.value=false}) {
                        Text(text = "Ok")
                    }

                }

            }
        }
    }

    val commentList = viewModel.commentListState.value

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Comments", fontSize = 20.sp, style = MaterialTheme.typography.h6
        )
        Spacer(modifier = Modifier.weight(1f))

        TextButton(
            onClick = { viewModel.dialogVisibilityState.value = true },
            modifier = Modifier.align(Alignment.Top)
        ) {
            Text(text = "Add New Comment", fontSize = 15.sp)
        }
    }
    //3 comments of first comments
    if (commentList.isEmpty()) {
        Log.v("empty", "empty comment list")
    } else if (commentList.size <= 3) {
        for (element in commentList) {
            CommentItem(comment = element)
        }
    } else if (commentList.size > 3) {
        for (i in 0 until 3) {
            CommentItem(comment = commentList[i])
        }
        MoreComment(product = viewModel.productState.value, navController = navController)
    }

    Spacer(modifier = Modifier.padding(bottom = 70.dp))
}

@Composable
fun MoreComment(product: Product, navController: NavHostController) {
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
                navController.navigate(MyScreens.CommentScreen.route + "/${product.productId}")
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
fun CommentItem(comment: Comment) {
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
                text = comment.userEmail,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth(),
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = comment.text,
                textAlign = TextAlign.Justify,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.padding(top = 5.dp)
            )
        }
    }
}

@Composable
fun ProductDetails(product: Product, commentList: List<Comment>) {
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
                    text = commentList.size.toString() + " Comments",
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
                    text = product.material,
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
                    text = product.soldItem + " Sold",
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
                text = product.tags,
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
fun ProductDescription(product: Product, navController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(top = 10.dp)
    ) {
        Text(text = product.name, style = MaterialTheme.typography.h6, fontSize = 20.sp)

        Spacer(modifier = Modifier.padding(top = 10.dp))

        Text(
            text = product.detailText,
            textAlign = TextAlign.Justify,
            lineHeight = 25.sp
        )

        TextButton(
            onClick = { navController.navigate(MyScreens.CategoryScreen.route + "/${product.category}") },
            modifier = Modifier.padding(5.dp)
        ) {
            Text(text = "#" + product.category)
        }
    }
}

@Composable
fun ProductImage(product: Product) {
    AsyncImage(
        model = product.imgUrl, contentDescription = "ProductImage",
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            .clip(Shapes.medium),
        contentScale = ContentScale.Crop
    )
}


@ExperimentalMaterial3Api
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ProductScreen(productId = "2")
}