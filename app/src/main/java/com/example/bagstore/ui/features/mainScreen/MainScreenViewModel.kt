package com.example.bagstore.ui.features.mainScreen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bagstore.model.data.Advertisement
import com.example.bagstore.model.data.Product
import com.example.bagstore.model.repository.product.ProductRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainScreenViewModel(
    private val productRepository: ProductRepository,
    isInternetConnected:Boolean
):ViewModel() {
    val productState= mutableStateOf<List<Product>>(listOf())
    val advertisementState= mutableStateOf<List<Advertisement>>(listOf())
    val showProgressBar= mutableStateOf(false)

    init {
        getAllDatsFormServer(isInternetConnected)
    }

    private fun getAllDatsFormServer(isInternetConnected: Boolean) {
        viewModelScope.launch{
            showProgressBar.value=true

            //delay for 2 second for fun :D
            delay(2000L)

            val loadedProduct=async {
                productRepository.insertAllProduct(isInternetConnected)
                productRepository.getAllProducts()
            }

            val loadedAdvertisement=async {productRepository.getAllAdvertisement(isInternetConnected)}

            updateDats(loadedProduct.await(),loadedAdvertisement.await())

            showProgressBar.value=false
            /*
            1- show progressbar at first
            2- async load data from server
            3- when both loaded update states ui
            4- dismiss progress bar
            and write this method on the constructor , because load data when create this class
             */
        }

    }

    private fun updateDats(productList: List<Product>, advertisementList: List<Advertisement>) {
        productState.value=productList
        advertisementState.value=advertisementList
    }
}

