package com.example.bagstore.model.data


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ProductResponse(
    val success: Boolean,
    val products: List<Product>
)

@Entity
@Parcelize
data class Product(
    @SerializedName("productId")
    @PrimaryKey
    val productId: String,
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
    @SerializedName("soldItem")
    val soldItem: String,
    @SerializedName("tags")
    val tags: String
) : Parcelable