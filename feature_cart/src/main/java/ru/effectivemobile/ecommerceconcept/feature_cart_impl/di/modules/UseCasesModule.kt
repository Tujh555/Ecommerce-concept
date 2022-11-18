package ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.modules

import dagger.Binds
import dagger.Module
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.useCases.GetUserCartUseCase
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.useCases.impls.GetUserCartUseCaseImpl

@Module
internal interface UseCasesModule {
    @Binds
    fun bindGetCartUseCase(case: GetUserCartUseCaseImpl): GetUserCartUseCase
}