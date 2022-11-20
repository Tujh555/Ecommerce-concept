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
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartDependencyProvider
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartFeatureComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.api.HomePageFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_phones.api.FeaturePhonesApi
import ru.effectivemobile.ecommerceconcept.feature_phones.api.FeaturePhonesDependencies
import ru.effectivemobile.ecommerceconcept.feature_phones.api.PhonesNavigationInfo
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.FeaturePhonesComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.PhonesDependencyProvider
import ru.effectivemobile.ecommerceconcept.navigation.NavigationInfo

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
    fun provideFeaturePhonesDependencies(service: StoreService, cartFeatureApi: CartFeatureApi): FeaturePhonesDependencies {
        return object : FeaturePhonesDependencies {
            override val service: StoreService
                get() = service
            override val cartNavigationInfo: NavigationInfo
                get() = cartFeatureApi.navigationInfo
        }
    }

    @Provides
    fun provideFeaturePhonesApi(dependencies: FeaturePhonesDependencies): FeaturePhonesApi {
        FeaturePhonesComponentHolder.init(dependencies)
        PhonesDependencyProvider.dependencies = dependencies
        return FeaturePhonesComponentHolder.get()
    }

    @Provides
    @AppScope
    fun provideFeatureHomePageDependencies(api: FeaturePhonesApi): HomePageFeatureDependencies {
        return object : HomePageFeatureDependencies {
            override val phonesNavigationInfo: PhonesNavigationInfo
                get() = api.navigationInfo
        }
    }

    @Provides
    fun provideCartFeatureApi(dependencies: CartFeatureDependencies): CartFeatureApi {
        CartFeatureComponentHolder.init(dependencies)
        CartDependencyProvider.dependencies = dependencies
        return CartFeatureComponentHolder.get()
    }

    @Provides
    @AppScope
    fun provideStoreService(): StoreService = NetworkApiProvider.get().service
}
