package ru.effectivemobile.apimodule.entities

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailedProduct(
    @SerializedName("CPU")
    val cpu: String,

    val camera: String,

    val capacity: List<String>,

    @SerializedName("color")
    val colors: List<String>,

    val id: String,

    @SerializedName("images")
    val imagesUrl: List<String>,

    val isFavorites: Boolean,

    val price: Float,

    val rating: Float,

    val sd: String,

    val ssd: String,

    val title: String
) : Parcelable
