package com.example.bagstore.model.data


import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

data class AdvertisementsResponse(
    val success: Boolean,
    @SerializedName("ads")
    val advertisement: List<Advertisement>
)

@Entity
@Parcelize
data class Advertisement(
    @SerializedName("adId")
    @PrimaryKey
    val adId: String,
    @SerializedName("imageURL")
    val imageURL: String,
    @SerializedName("productId")
    val productId: String
) : Parcelable