package ru.effectivemobile.ecommerceconcept.feature_phones.api

import ru.effectivemobile.ecommerceconcept.module_injector.BaseApi
import ru.effectivemobile.ecommerceconcept.navigation.NavigationInfo
import java.lang.ref.WeakReference

interface FeaturePhonesApi : BaseApi {
    val navigationInfo: PhonesNavigationInfo
}