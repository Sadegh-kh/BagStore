package com.example.bagstore.model.repository.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.bagstore.model.data.Advertisement
import com.example.bagstore.model.data.Product

@Database(entities = [Product::class], version = 1, exportSchema = false)
abstract class MyDatabase:RoomDatabase() {
    abstract val productDao:ProductDao
}