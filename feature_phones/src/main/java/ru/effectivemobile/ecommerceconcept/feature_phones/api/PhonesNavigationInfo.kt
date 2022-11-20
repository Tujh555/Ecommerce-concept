package ru.effectivemobile.ecommerceconcept.feature_phones.api

import ru.effectivemobile.ecommerceconcept.navigation.NavigationInfo
import javax.inject.Inject

class PhonesNavigationInfo @Inject constructor() {
    private val actionId: Int = ru.effectivemobile.ecommerceconcept.feature_phones.R.id.action_global_to_phonesFragment
    private val hostId: Int = ru.effectivemobile.ecommerceconcept.navigation.R.id.host_main

    fun toNavigationInfo() = NavigationInfo(actionId, hostId)
}