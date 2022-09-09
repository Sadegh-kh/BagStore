package com.example.bagstore.model.data


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable

data class ProductResponse(
    val success: Boolean,
    val products: List<Product>
)

@Parcelize
data class Product(
    @SerializedName("category")
    val category: String,
    @SerializedName("detailText")
    val detailText: String,
    @SerializedName("imgUrl")
    val imgUrl: String,
    @SerializedName("material")
    val material: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("productId")
    val productId: String,
    @SerializedName("soldItem")
    val soldItem: String,
    @SerializedName("tags")
    val tags: String
) : Parcelable