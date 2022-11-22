package ru.effectivemobile.ecommerceconcept.feature_cart_api

import ru.effectivemobile.ecommerceconcept.module_injector.BaseApi
import ru.effectivemobile.ecommerceconcept.navigation.NavigationInfo

/**
 * @property navigationInfo - information for graph to navigate
 * @property countProvider - object for getting products count in cart
 */
interface CartFeatureApi : BaseApi {
    val navigationInfo: CartNavigationInfo
    val countProvider: ProductCartCountProvider
}