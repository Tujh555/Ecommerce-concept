package ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.entities

internal data class Cart(
    val basket: List<CartProduct>,
    val delivery: String,
    val id: String,
    val total: Float
) {
    val ownData: OwnCartData
        get() = OwnCartData(
            delivery = delivery,
            id = id,
            total = total
        )

    data class OwnCartData(
        val delivery: String,
        val id: String,
        val total: Float
    )
}