package ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.useCases

import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.entities.Cart

internal interface GetUserCartUseCase {
    suspend operator fun invoke(userId: String = ""): Cart
}