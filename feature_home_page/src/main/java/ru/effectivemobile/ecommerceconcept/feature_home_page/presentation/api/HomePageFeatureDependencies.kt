package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.api

import ru.effectivemobile.ecommerceconcept.feature_phones.api.PhonesNavigationInfo
import ru.effectivemobile.ecommerceconcept.module_injector.BaseDependencies
import ru.effectivemobile.ecommerceconcept.navigation.NavigationInfo

interface HomePageFeatureDependencies : BaseDependencies {
    val phonesNavigationInfo: PhonesNavigationInfo
}