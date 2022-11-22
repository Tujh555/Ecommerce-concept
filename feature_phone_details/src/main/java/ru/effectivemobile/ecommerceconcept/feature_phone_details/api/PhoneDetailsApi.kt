package ru.effectivemobile.ecommerceconcept.feature_phone_details.api

import ru.effectivemobile.ecommerceconcept.module_injector.BaseApi

interface PhoneDetailsApi : BaseApi {
    val navigationInfo: PhoneDetailsNavigationInfo
}