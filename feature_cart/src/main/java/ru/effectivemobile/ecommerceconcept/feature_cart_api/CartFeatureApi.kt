package ru.effectivemobile.ecommerceconcept.feature_cart_api

import ru.effectivemobile.ecommerceconcept.module_injector.BaseApi
import ru.effectivemobile.ecommerceconcept.navigation.NavigationInfo

interface CartFeatureApi : BaseApi {
    val navigationInfo: NavigationInfo
}