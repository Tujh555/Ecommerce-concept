package ru.effectivemobile.apimodule.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

//{
//    "basket": [
//    {
//        "id": 1,
//        "images": "https://www.manualspdf.ru/thumbs/products/l/1260237-samsung-galaxy-note-20-ultra.jpg",
//        "price": 1500,
//        "title": "Galaxy Note 20 Ultra"
//    },
//
//    ],
//    "delivery": "Free",
//    "id": "4",
//    "total": 3300
//}

@Parcelize
data class Cart(
   val basket: List<String>,
   val delivery: String,
   val id: String,
   val total: Float
) : Parcelable