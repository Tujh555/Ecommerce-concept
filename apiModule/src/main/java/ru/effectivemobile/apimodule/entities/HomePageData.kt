package ru.effectivemobile.apimodule.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomePageData(
    @SerializedName("home_store")
    val homeStore: List<HomeStoreProduct>,

    @SerializedName("best_seller")
    val bestSeller: List<BestSellerProduct>
) : Parcelable