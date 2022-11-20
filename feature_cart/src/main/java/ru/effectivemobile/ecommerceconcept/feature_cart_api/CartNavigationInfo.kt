package ru.effectivemobile.ecommerceconcept.feature_cart_api

import ru.effectivemobile.ecommerceconcept.feature_cart.R
import ru.effectivemobile.ecommerceconcept.navigation.NavigationInfo
import javax.inject.Inject

class CartNavigationInfo @Inject constructor() {
    private val actionId = R.id.action_global_navigate_to_cartFragment
    private val hostId = ru.effectivemobile.ecommerceconcept.navigation.R.id.host_global

    fun toNavigationInfo() = NavigationInfo(actionId, hostId)
}