package ru.effectivemobile.ecommerceconcept.navigation

open class NavigationInfo(
    open val actionId: Int,
    open val hostId: Int? = null,
) {
    override fun toString() = "Action = $actionId, Host = $hostId"
}