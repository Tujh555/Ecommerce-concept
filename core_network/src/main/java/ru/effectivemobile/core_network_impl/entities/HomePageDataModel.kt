package ru.effectivemobile.core_network_impl.entities

import com.google.gson.annotations.SerializedName

data class HomePageDataModel(
    @SerializedName("home_store")
    val homeStore: List<HomeStoreProductModel>,

    @SerializedName("best_seller")
    val bestSeller: List<BestSellerProductModel>
)