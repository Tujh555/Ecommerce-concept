package ru.effectivemobile.ecommerceconcept.feature_phone_details.impl.di

import ru.effectivemobile.ecommerceconcept.feature_phone_details.api.PhoneDetailDependencies
import ru.effectivemobile.ecommerceconcept.feature_phone_details.api.PhoneDetailsApi
import ru.effectivemobile.ecommerceconcept.module_injector.ComponentHolder

object PhoneDetailsComponentHolder : ComponentHolder<PhoneDetailsApi, PhoneDetailDependencies> {
    private var component: PhoneDetailsComponent? = null
    internal val phoneComponent: PhoneDetailsComponent
        get() = requireNotNull(component) {
            "PhoneDetailsComponent was not initialized"
        }

    override fun init(dependencies: PhoneDetailDependencies) {
        if (component == null) {
            synchronized(PhoneDetailsComponentHolder::class) {
                if (component == null) {
                    component = PhoneDetailsComponent.get(dependencies)
                }
            }
        }
    }

    override fun get(): PhoneDetailsApi = requireNotNull(component) {
        "PhoneDetailsApi was not initialized"
    }

    override fun reset() {
        component = null
    }
}