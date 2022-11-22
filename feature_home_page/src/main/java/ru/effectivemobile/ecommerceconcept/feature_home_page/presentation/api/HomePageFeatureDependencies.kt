package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.api

import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartNavigationInfo
import ru.effectivemobile.ecommerceconcept.feature_cart_api.ProductCartCountProvider
import ru.effectivemobile.ecommerceconcept.feature_phones.api.PhonesNavigationInfo
import ru.effectivemobile.ecommerceconcept.module_injector.BaseDependencies
import ru.effectivemobile.ecommerceconcept.navigation.NavigationInfo

/**
 * @property phonesNavigationInfo - navigation info for transition to Phones fragment
 * @property cartNavigationInfo - navigation info for transition to Cart fragment
 * @property countProvider
 * @see ProductCartCountProvider
 */
interface HomePageFeatureDependencies : BaseDependencies {
    val phonesNavigationInfo: PhonesNavigationInfo
    val cartNavigationInfo: CartNavigationInfo
    val countProvider: ProductCartCountProvider
}