package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl.di

import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartNavigationInfo
import ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.api.HomePageFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_phones.api.PhonesNavigationInfo
import ru.effectivemobile.ecommerceconcept.module_injector.BaseApi
import ru.effectivemobile.ecommerceconcept.module_injector.ComponentHolder

object HomePageComponentHolder : ComponentHolder<BaseApi, HomePageFeatureDependencies> {
    internal var phonesNavigationInfo: PhonesNavigationInfo? = null
    internal var cartNavigationInfo: CartNavigationInfo? = null

    override fun init(dependencies: HomePageFeatureDependencies) {
        phonesNavigationInfo = dependencies.phonesNavigationInfo
        cartNavigationInfo = dependencies.cartNavigationInfo
    }

    override fun get(): BaseApi = object : BaseApi {}

    override fun reset() { }
}