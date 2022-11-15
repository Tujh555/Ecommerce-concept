package ru.effectivemobile.apimodule.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BestSellerProduct(
    val id: Int,

    @SerializedName("is_favorites")
    val isFavorites: Boolean,

    val title: String,

    @SerializedName("price_without_discount")
    val priceWithDiscount: Float,

    @SerializedName("discount_price")
    val priceWithoutDiscount: Float,

    @SerializedName("picture")
    val pictureUrl: String
) : Parcelable