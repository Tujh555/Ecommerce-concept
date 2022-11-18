package ru.effectivemobile.core_network_impl

import android.util.Log
import dagger.Component
import ru.effectivemobile.core_network_api.CoreNetworkApi
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface CoreNetworkComponent : CoreNetworkApi

object NetworkApiProvider {
    private var instance: CoreNetworkComponent? = null

    fun get(): CoreNetworkApi {
        if (instance == null) {
            synchronized(CoreNetworkComponent::class) {
                if (instance == null) {
                    Log.d("MyLogs", "Core network component created")
                    instance = DaggerCoreNetworkComponent.create()
                }
            }
        }

        return instance!!
    }
}