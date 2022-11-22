package ru.effectivemobile.ecommerceconcept.feature_phone_details.api

import ru.effectivemobile.core_network_api.StoreService
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartNavigationInfo
import ru.effectivemobile.ecommerceconcept.module_injector.BaseDependencies

interface PhoneDetailDependencies : BaseDependencies {
    val storeService: StoreService
    val cartNavigationInfo: CartNavigationInfo
}