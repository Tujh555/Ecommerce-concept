package ru.effectivemobile.core_network.entities

import android.os.Parcelable

data class Cart(
   val basket: List<String>,
   val delivery: String,
   val id: String,
   val total: Float
)