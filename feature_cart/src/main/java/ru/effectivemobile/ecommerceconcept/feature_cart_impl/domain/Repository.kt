package ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain

import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.entities.Cart

internal interface Repository {
    suspend fun getUserCart(userId: String): Cart
}