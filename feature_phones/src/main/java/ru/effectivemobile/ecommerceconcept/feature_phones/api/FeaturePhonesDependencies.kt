package ru.effectivemobile.ecommerceconcept.feature_phones.api

import ru.effectivemobile.core_network_api.StoreService
import ru.effectivemobile.ecommerceconcept.feature_phone_details.api.PhoneDetailsNavigationInfo
import ru.effectivemobile.ecommerceconcept.module_injector.BaseDependencies

interface FeaturePhonesDependencies : BaseDependencies {
    val service: StoreService
    val phoneDetailsNavigationInfo: PhoneDetailsNavigationInfo
}