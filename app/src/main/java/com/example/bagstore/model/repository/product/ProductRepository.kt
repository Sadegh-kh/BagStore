package com.example.bagstore.model.repository.product

import com.example.bagstore.model.data.Advertisement
import com.example.bagstore.model.data.Product

interface ProductRepository {

    suspend fun getAllProducts(): List<Product>
    suspend fun getAllAdvertisement(isInternetConnected:Boolean): List<Advertisement>

    suspend fun insertAllProduct(isInternetConnected: Boolean)

    suspend fun getProductsByCategoryName(categoryName:String):List<Product>

    suspend fun getProductById(productId:String):Product

}