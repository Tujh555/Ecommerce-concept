package ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.modules

import dagger.Binds
import dagger.Module
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.data.RepositoryImpl
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartScope
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.domain.Repository

@Module
internal interface RepositoryModule {
    @Binds
    @CartScope
    fun bindRepositoryImpl(repo: RepositoryImpl): Repository
}