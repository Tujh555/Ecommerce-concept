package ru.effectivemobile.ecommerceconcept.di

import android.app.Application
import dagger.Component
import dagger.Module
import dagger.Provides
import ru.effectivemobile.core_network_api.StoreService
import ru.effectivemobile.core_network_impl.NetworkApiProvider
import ru.effectivemobile.ecommerceconcept.MainActivity
import ru.effectivemobile.ecommerceconcept.di.scopes.AppScope
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureApi
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartFeatureComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_phones.api.FeaturePhonesDependencies

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
    fun provideFeatureCartDependencies(service: StoreService): CartFeatureDependencies {
        return object : CartFeatureDependencies {
            override val storeService: StoreService
                get() = service
        }
    }

    @AppScope
    @Provides
    fun provideFeaturePhonesDependencies(service: StoreService): FeaturePhonesDependencies {
        return object : FeaturePhonesDependencies {
            override val service: StoreService
                get() = service
        }
    }

    @Provides
    fun provideCartFeatureApi(dependencies: CartFeatureDependencies): CartFeatureApi {
        CartFeatureComponentHolder.init(dependencies)
        return CartFeatureComponentHolder.get()
    }

    @Provides
    @AppScope
    fun provideStoreService(): StoreService = NetworkApiProvider.get().service
}
