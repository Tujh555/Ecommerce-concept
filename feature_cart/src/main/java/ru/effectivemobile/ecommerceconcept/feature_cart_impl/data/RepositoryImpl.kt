package ru.effectivemobile.ecommerceconcept.feature_cart_impl.data

import ru.effectivemobile.core_network_api.StoreService
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartScope
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.Repository
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.entities.Cart
import javax.inject.Inject

@CartScope
internal class RepositoryImpl @Inject constructor(
    private val storeService: StoreService
) : Repository {
    override suspend fun getUserCart(userId: String): Cart {
        return storeService.getCart().toCart()
    }
}