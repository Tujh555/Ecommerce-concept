package ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.entities

internal data class CartProduct(
    val id: Int,
    val imageUrl: String,
    val price: Float,
    val title: String,
) {
    var countInCart: Int = 1
        set(value) {
            if (value < 0) return

            field = value
        }
}