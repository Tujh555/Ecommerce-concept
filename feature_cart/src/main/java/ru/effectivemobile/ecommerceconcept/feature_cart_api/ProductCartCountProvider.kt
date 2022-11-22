package ru.effectivemobile.ecommerceconcept.feature_cart_api

import androidx.lifecycle.LiveData

interface ProductCartCountProvider {
    suspend fun getProductCount(): Int
}