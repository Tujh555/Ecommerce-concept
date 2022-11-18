package ru.effectivemobile.core_network_impl.entities

import com.google.gson.annotations.SerializedName

data class BasketProductModel(
    val id: Int = 0,

    @SerializedName("images")
    val imageUrl: String = "",

    val price: Float = 0.0f,

    val title: String = ""
)