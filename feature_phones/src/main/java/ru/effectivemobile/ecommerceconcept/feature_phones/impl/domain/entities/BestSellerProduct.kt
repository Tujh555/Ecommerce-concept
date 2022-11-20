package ru.effectivemobile.ecommerceconcept.feature_phones.impl.domain.entities

internal data class BestSellerProduct(
    val id: Int,
    var isFavorites: Boolean,
    val title: String,
    val priceWithDiscount: Float,
    val priceWithoutDiscount: Float,
    val pictureUrl: String
)
