package com.example.bagstore.ui.features.productScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore.model.data.Comment
import com.example.bagstore.model.data.Product
import com.example.bagstore.model.repository.comment.CommentRepository
import com.example.bagstore.model.repository.product.ProductRepository
import com.example.bagstore.util.ProductEmpty
import com.example.bagstore.util.coroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class ProductScreenViewModel(
    productId: String,
    private val productRepository: ProductRepository,
    private val commentRepository: CommentRepository
) : ViewModel() {
    val productState = mutableStateOf<Product>(ProductEmpty)
    val commentListState = mutableStateOf<List<Comment>>(listOf())
    val dialogVisibilityState = mutableStateOf(false)
    val textFieldState = mutableStateOf("")

    init {
        getProductData(productId)
    }

    private fun getProductData(productId: String) {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            val product = async { productRepository.getProductById(productId) }
            val comments = async{ commentRepository.getAllComment(productId) }
            loadData(product.await(),comments.await())
        }
    }

    private fun loadData(product: Product, commentList: List<Comment>) {
        productState.value=product
        commentListState.value=commentList
    }

}