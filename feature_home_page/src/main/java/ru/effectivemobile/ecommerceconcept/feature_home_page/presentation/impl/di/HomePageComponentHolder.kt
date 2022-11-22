package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl.di

import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartNavigationInfo
import ru.effectivemobile.ecommerceconcept.feature_cart_api.ProductCartCountProvider
import ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.api.HomePageFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_phones.api.PhonesNavigationInfo
import ru.effectivemobile.ecommerceconcept.module_injector.BaseApi
import ru.effectivemobile.ecommerceconcept.module_injector.ComponentHolder

/**
 * @see ComponentHolder
 */
object HomePageComponentHolder : ComponentHolder<BaseApi, HomePageFeatureDependencies> {
    internal var phonesNavigationInfo: PhonesNavigationInfo? = null
    internal var cartNavigationInfo: CartNavigationInfo? = null
    internal var cartCountProvider: ProductCartCountProvider? = null

    override fun init(dependencies: HomePageFeatureDependencies) {
        phonesNavigationInfo = dependencies.phonesNavigationInfo
        cartNavigationInfo = dependencies.cartNavigationInfo
        cartCountProvider = dependencies.countProvider
    }

    override fun get(): BaseApi = object : BaseApi {}

    override fun reset() {
        phonesNavigationInfo = null
        cartNavigationInfo = null
        cartCountProvider = null
    }
}