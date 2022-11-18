package ru.effectivemobile.core_network_impl.entities

import com.google.gson.annotations.SerializedName

data class BestSellerProductModel(
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
)