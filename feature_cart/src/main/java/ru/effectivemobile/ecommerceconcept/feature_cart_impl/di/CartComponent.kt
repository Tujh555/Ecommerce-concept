package ru.effectivemobile.ecommerceconcept.feature_cart_impl.di

import dagger.Component
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureApi
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.modules.RepositoryModule
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.modules.UseCasesModule
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.modules.ViewModelModule
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.presentation.CartFragment

@Component(
    dependencies = [CartFeatureDependencies::class],
    modules = [
        ViewModelModule::class,
        RepositoryModule::class,
        UseCasesModule::class
    ]
)
@CartScope
internal abstract class CartComponent : CartFeatureApi {
    abstract fun inject(fragment: CartFragment)

    @Component.Builder
    interface Builder {
        fun dependencies(dependencies: CartFeatureDependencies): Builder

        fun build(): CartComponent
    }

    companion object {
        fun get(dependencies: CartFeatureDependencies): CartComponent = DaggerCartComponent
            .builder()
            .dependencies(dependencies)
            .build()
    }
}