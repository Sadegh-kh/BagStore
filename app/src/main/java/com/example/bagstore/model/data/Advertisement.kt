package com.example.bagstore.model.data


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import coil.compose.AsyncImagePainter

data class AdvertisementsResponse(
    val success: Boolean,
    val advertisement: List<Advertisement>
)

@Parcelize
data class Advertisement(
    @SerializedName("adId")
    val adId: String,
    @SerializedName("imageURL")
    val imageURL: String,
    @SerializedName("productId")
    val productId: String
) : Parcelable