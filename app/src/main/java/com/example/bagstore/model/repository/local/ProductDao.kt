package com.example.bagstore.model.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bagstore.model.data.Advertisement
import com.example.bagstore.model.data.Product

@Dao
interface ProductDao {

    @Query("SELECT * FROM Product")
    suspend fun getAllProducts():List<Product>

    @Query("SELECT * FROM Product WHERE productId = :idProduct")
    suspend fun getProductById(idProduct:String):Product


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insetAllProducts(productList: List<Product>)


}
