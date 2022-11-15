package ru.effectivemobile.core_network.entities

import com.google.gson.annotations.SerializedName

data class HomeStoreProduct(
    val id: Int,

    val title: String,

    val subtitle: String,

    @SerializedName("picture")
    val pictureUrl: String,

    @SerializedName("is_buy")
    val isBuy: Boolean
)