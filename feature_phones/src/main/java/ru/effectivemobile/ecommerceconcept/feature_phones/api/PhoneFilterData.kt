package ru.effectivemobile.ecommerceconcept.feature_phones.api

interface PhoneFilterData {
    val brand: String
    val priceRange: IntRange
    val sizeRange: Pair<Float, Float>
}