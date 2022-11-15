package ru.effectivemobile.apimodule.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class HomeStoreProduct(
    val id: Int,

    val title: String,

    val subtitle: String,

    @SerializedName("picture")
    val pictureUrl: String,

    @SerializedName("is_buy")
    val isBuy: Boolean
) : Parcelable