package ru.effectivemobile.ecommerceconcept.feature_cart_impl.di

import dagger.BindsInstance
import dagger.Component
import ru.effectivemobile.ecommerceconcept.feature_cart.R
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureApi
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.modules.RepositoryModule
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.modules.UseCasesModule
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.modules.ViewModelModule
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.presentation.CartFragment
import ru.effectivemobile.ecommerceconcept.navigation.NavigationInfo

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

        @BindsInstance
        fun navInfo(info: NavigationInfo): Builder

        fun build(): CartComponent
    }

    companion object {
        fun get(dependencies: CartFeatureDependencies): CartComponent = DaggerCartComponent
            .builder()
            .navInfo(NavigationInfo(R.id.action_global_navigate_to_cartFragment, ru.effectivemobile.ecommerceconcept.navigation.R.id.host_global))
            .dependencies(dependencies)
            .build()
    }
}