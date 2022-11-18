package ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.useCases.impls

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.Repository
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.entities.Cart
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.useCases.GetUserCartUseCase
import javax.inject.Inject

internal class GetUserCartUseCaseImpl @Inject constructor(
    private val repository: Repository
) : GetUserCartUseCase {
    override suspend fun invoke(userId: String): Cart = withContext(Dispatchers.IO) {
        repository.getUserCart(userId)
    }
}