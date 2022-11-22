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
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartNavigationInfo
import ru.effectivemobile.ecommerceconcept.feature_cart_api.ProductCartCountProvider
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartDependencyProvider
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.di.CartFeatureComponentHolder
import ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.api.HomePageFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_phone_details.api.PhoneDetailDependencies
import ru.effectivemobile.ecommerceconcept.feature_phone_details.api.PhoneDetailsApi
import ru.effectivemobile.ecommerceconcept.feature_phone_details.api.PhoneDetailsNavigationInfo
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.di.FeaturePhoneDetailsDependenciesProvider
import ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.di.PhoneDetailsComponentHolder
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
    fun provideFeaturePhonesDependencies(service: StoreService, phoneDetailsApi: PhoneDetailsApi): FeaturePhonesDependencies {
        return object : FeaturePhonesDependencies {
            override val service: StoreService
                get() = service
            override val phoneDetailsNavigationInfo: PhoneDetailsNavigationInfo
                get() = phoneDetailsApi.navigationInfo
        }
    }

    @Provides
    fun provideFeaturePhonesApi(dependencies: FeaturePhonesDependencies): FeaturePhonesApi {
        FeaturePhonesComponentHolder.init(dependencies)
        PhonesDependencyProvider.dependencies = dependencies
        return FeaturePhonesComponentHolder.get()
    }

    @Provides
    fun providePhoneDetailsApi(dependencies: PhoneDetailDependencies): PhoneDetailsApi {
        PhoneDetailsComponentHolder.init(dependencies)
        FeaturePhoneDetailsDependenciesProvider.dependencies = dependencies
        return PhoneDetailsComponentHolder.get()
    }

    @Provides
    fun providePhoneDetailsDependencies(service: StoreService, cartFeatureApi: CartFeatureApi): PhoneDetailDependencies {
        return object : PhoneDetailDependencies {
            override val storeService: StoreService
                get() = service
            override val cartNavigationInfo: CartNavigationInfo
                get() = cartFeatureApi.navigationInfo

        }
    }

    @Provides
    @AppScope
    fun provideFeatureHomePageDependencies(api: FeaturePhonesApi, cartFeatureApi: CartFeatureApi): HomePageFeatureDependencies {
        return object : HomePageFeatureDependencies {
            override val phonesNavigationInfo: PhonesNavigationInfo
                get() = api.navigationInfo
            override val cartNavigationInfo: CartNavigationInfo
                get() = cartFeatureApi.navigationInfo
            override val countProvider: ProductCartCountProvider
                get() = cartFeatureApi.countProvider
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
