package ru.effectivemobile.ecommerceconcept.feature_phone_details.api

import ru.effectivemobile.ecommerceconcept.feature_phone_details.R
import ru.effectivemobile.ecommerceconcept.navigation.NavigationInfo
import javax.inject.Inject

class PhoneDetailsNavigationInfo @Inject constructor() {
    private val actionId = R.id.action_global_to_phoneDetailsFragment
    private val hostID = ru.effectivemobile.ecommerceconcept.navigation.R.id.host_global

    fun toNavigationInfo() = NavigationInfo(actionId, hostID)
}