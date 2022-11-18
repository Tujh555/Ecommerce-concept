package ru.effectivemobile.ecommerceconcept.feature_cart_api

import ru.effectivemobile.core_network_api.StoreService
import ru.effectivemobile.ecommerceconcept.module_injector.BaseDependencies

interface CartFeatureDependencies : BaseDependencies {
    val storeService: StoreService
    // TODO get the phone details api
}