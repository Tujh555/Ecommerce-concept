package ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.impl.di

import android.util.Log
import ru.effectivemobile.ecommerceconcept.feature_home_page.presentation.api.HomePageFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_phones.api.PhonesNavigationInfo
import ru.effectivemobile.ecommerceconcept.module_injector.BaseApi
import ru.effectivemobile.ecommerceconcept.module_injector.ComponentHolder
import ru.effectivemobile.ecommerceconcept.navigation.NavigationInfo

object HomePageComponentHolder : ComponentHolder<BaseApi, HomePageFeatureDependencies> {
    internal var navigationInfo: PhonesNavigationInfo? = null

    override fun init(dependencies: HomePageFeatureDependencies) {
        navigationInfo = dependencies.phonesNavigationInfo
        Log.d("MyLogs", "HomaPageHolder $navigationInfo")
    }

    override fun get(): BaseApi = object : BaseApi {}

    override fun reset() { }
}