package ru.effectivemobile.apimodule.entities

import com.google.gson.annotations.SerializedName

//{
//        "id": 2,
//        "images": "https://store.storeimages.cdn-apple.com/4668/as-images.apple.com/is/iphone-13-pro-silver-select?wid=470&hei=556&fmt=jpeg&qlt=95&.v=1631652954000",
//        "price": 1800,
//        "title": "iPhone 13"
//    }

data class BasketProduct(
    val id: Int,

    @SerializedName("images")
    val imageUrl: String,

    val price: Float,

    val title: String
)