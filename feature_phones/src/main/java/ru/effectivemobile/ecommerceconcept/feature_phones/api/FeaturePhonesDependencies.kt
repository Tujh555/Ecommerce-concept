package ru.effectivemobile.ecommerceconcept.feature_phones.api

import ru.effectivemobile.core_network_api.StoreService
import ru.effectivemobile.ecommerceconcept.feature_phones.impl.di.qualifiers.PhonesInfo
import ru.effectivemobile.ecommerceconcept.module_injector.BaseDependencies
import ru.effectivemobile.ecommerceconcept.navigation.NavigationInfo

interface FeaturePhonesDependencies : BaseDependencies {
    val service: StoreService
    val cartNavigationInfo: NavigationInfo
}