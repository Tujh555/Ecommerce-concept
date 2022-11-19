package ru.effectivemobile.core_network_impl.entities

import com.google.gson.annotations.SerializedName

data class HomeStoreProductModel(
    val id: Int,

    @SerializedName("is_new")
    val isNew: Boolean = false,

    val title: String,

    val subtitle: String,

    @SerializedName("picture")
    val pictureUrl: String,

    @SerializedName("is_buy")
    val isBuy: Boolean
)