package ru.effectivemobile.ecommerceconcept.di

import android.app.Application
import android.util.Log
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.delay
import ru.effectivemobile.core_network_api.StoreService
import ru.effectivemobile.core_network_impl.CoreNetworkComponent
import ru.effectivemobile.core_network_impl.NetworkApiProvider
import ru.effectivemobile.core_network_impl.entities.BasketProductModel
import ru.effectivemobile.core_network_impl.entities.CartModel
import ru.effectivemobile.core_network_impl.entities.DetailedProductModel
import ru.effectivemobile.core_network_impl.entities.HomePageDataModel
import ru.effectivemobile.ecommerceconcept.MainActivity
import ru.effectivemobile.ecommerceconcept.di.scopes.AppScope
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureApi
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartFeatureComponentHolder

@Component(modules = [AppModule::class])
@AppScope
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(app: Application)

    @Component.Builder
    interface Builder {

        fun build(): AppComponent
    }
}

@Module
class AppModule {
    @AppScope
    @Provides
    fun provideFeatureCartDependencies(): CartFeatureDependencies {
        return object : CartFeatureDependencies {
            override val storeService: StoreService
                get() = NetworkApiProvider.get().service
        }
    }

    @Provides
    fun provideCartFeatureApi(dependencies: CartFeatureDependencies): CartFeatureApi {
        CartFeatureComponentHolder.init(dependencies)
        return CartFeatureComponentHolder.get()
    }
}
