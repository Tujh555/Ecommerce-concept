package ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.modules

import dagger.Binds
import dagger.Module
import ru.effectivemobile.ecommerceconcept.feature_cart_api.ProductCartCountProvider
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.data.CartCountProviderImpl

@Module
internal interface ApiModule {
    @Binds
    fun bindProvider(providerImpl: CartCountProviderImpl): ProductCartCountProvider
}