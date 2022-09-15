package com.example.bagstore.ui.features.categoryScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore.model.data.Product
import com.example.bagstore.model.repository.product.ProductRepository
import kotlinx.coroutines.launch


class CategoryScreenViewModel(
    category:String,
    private val productRepository: ProductRepository
):ViewModel() {

    val categoryProductState= mutableStateOf(listOf<Product>())

    init {
       getProductByCategoryName(category)
    }
    private fun getProductByCategoryName(category: String){
        viewModelScope.launch {
            val categoryProduct=productRepository.getProductsByCategoryName(category)
            categoryProductState.value=categoryProduct
        }
    }

}