package ru.effectivemobile.ecommerceconcept.feature_cart_impl.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.effectivemobile.ecommerceconcept.feature_cart_api.ProductCartCountProvider
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.Repository
import javax.inject.Inject

internal class CartCountProviderImpl @Inject constructor(
    private val repository: Repository
) : ProductCartCountProvider {
    override suspend fun getProductCount(): Int {
        return withContext(Dispatchers.IO) {
            repository.getUserCart("").basket.size
        }
    }
}