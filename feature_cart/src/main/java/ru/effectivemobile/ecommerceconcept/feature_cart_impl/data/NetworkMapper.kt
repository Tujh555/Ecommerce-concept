package ru.effectivemobile.ecommerceconcept.feature_cart_impl.data

import ru.effectivemobile.core_network_impl.entities.BasketProductModel
import ru.effectivemobile.core_network_impl.entities.CartModel
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.entities.Cart
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.entities.CartProduct

internal fun CartModel.toCart(): Cart = Cart(
    basket = basket.map { it.toCartProduct() },
    delivery = delivery,
    id = id,
    total = total
)

internal fun BasketProductModel.toCartProduct(): CartProduct = CartProduct(
    id = id,
    imageUrl = imageUrl,
    price = price,
    title = title
)