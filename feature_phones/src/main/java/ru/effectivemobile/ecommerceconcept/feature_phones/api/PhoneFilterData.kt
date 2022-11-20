package ru.effectivemobile.ecommerceconcept.feature_phones.api

import android.os.Parcelable

interface PhoneFilterData : Parcelable {
    val brand: String
    val priceTop: Int
    val priceBottom: Int
    val sizeTop: Float
    val sizeBottom: Float
}