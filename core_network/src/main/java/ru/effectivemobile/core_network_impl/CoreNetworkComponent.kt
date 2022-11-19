package ru.effectivemobile.core_network_impl

import dagger.Component
import ru.effectivemobile.core_network_api.CoreNetworkApi
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
internal interface CoreNetworkComponent : CoreNetworkApi

object NetworkApiProvider {
    fun get(): CoreNetworkApi {
        return  DaggerCoreNetworkComponent.create()
    }
}