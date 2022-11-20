package ru.effectivemobile.ecommerceconcept.feature_phones.impl.presentation

internal interface PhoneFilterData {
    val brand: String
    val priceTop: Int
    val priceBottom: Int
    val sizeTop: Float
    val sizeBottom: Float
}