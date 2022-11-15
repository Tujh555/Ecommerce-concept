package ru.effectivemobile.core_network.entities

import com.google.gson.annotations.SerializedName

data class BasketProduct(
    val id: Int,

    @SerializedName("images")
    val imageUrl: String,

    val price: Float,

    val title: String
)