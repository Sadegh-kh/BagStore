package com.example.bagstore.model.repository.product

import com.example.bagstore.model.data.Advertisement
import com.example.bagstore.model.data.Product
import com.example.bagstore.model.net.ApiService
import com.example.bagstore.model.repository.local.ProductDao


class ProductRepositoryImp(
    private val apiService: ApiService,
    private val productDao: ProductDao
):ProductRepository {
    override suspend fun getAllProducts(): List<Product> {
        return productDao.getAllProducts()
    }

    override suspend fun getAllAdvertisement(isInternetConnected:Boolean): List<Advertisement> {
        if (isInternetConnected){
            if (apiService.getAllAdvertisements().success){
                return apiService.getAllAdvertisements().advertisement
            }
        }
        return listOf()
    }

    override suspend fun insertAllProduct(isInternetConnected: Boolean){
        if (isInternetConnected){
            val response=apiService.getAllProducts()
            if (response.success){
                productDao.insetAllProducts(response.products)
            }
        }else{
           val lastLoadedProducts= productDao.getAllProducts()
            productDao.insetAllProducts(lastLoadedProducts)
        }

    }

}