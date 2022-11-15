package ru.effectivemobile.core_network.entities

import com.google.gson.annotations.SerializedName

data class HomePageData(
    @SerializedName("home_store")
    val homeStore: List<HomeStoreProduct>,

    @SerializedName("best_seller")
    val bestSeller: List<BestSellerProduct>
)