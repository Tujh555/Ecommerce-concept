package ru.effectivemobile.core_network_api

import ru.effectivemobile.ecommerceconcept.module_injector.BaseApi

interface CoreNetworkApi : BaseApi {
    val service: StoreService
}