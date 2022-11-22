package ru.effectivemobile.ecommerceconcept.feature_cart_api

/**
 * Allows you to get the number of products in the cart
 */
interface ProductCartCountProvider {
    suspend fun getProductCount(): Int
}