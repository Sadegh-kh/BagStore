package com.example.bagstore.ui.features.productScreen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore.model.data.Product
import com.example.bagstore.model.repository.product.ProductRepository
import com.example.bagstore.util.coroutineExceptionHandler
import kotlinx.coroutines.launch

class ProductScreenViewModel(productId: String,private val productRepository: ProductRepository) : ViewModel() {
    val productState = mutableStateOf<Product>(Product("","","","","","","","",""))
    val dialogVisibilityState= mutableStateOf(false)
    val textFieldState= mutableStateOf("")
    init {
        getProductById(productId)
    }
    private fun getProductById(productId: String) {
        viewModelScope.launch(context = coroutineExceptionHandler) {
            productState.value=productRepository.getProductById(productId)
        }
    }

}