package ru.effectivemobile.ecommerceconcept.feature_phones.impl.di

import android.util.Log
import ru.effectivemobile.ecommerceconcept.feature_phones.api.FeaturePhonesApi
import ru.effectivemobile.ecommerceconcept.feature_phones.api.FeaturePhonesDependencies
import ru.effectivemobile.ecommerceconcept.module_injector.ComponentHolder

object FeaturePhonesComponentHolder : ComponentHolder<FeaturePhonesApi, FeaturePhonesDependencies> {
    private var component: PhonesComponent? = null

    internal val featureComponent: PhonesComponent
        get() = requireNotNull(component) {
            "Phones component wasn't initialized"
        }

    override fun init(dependencies: FeaturePhonesDependencies) {
        if (component == null) {
            synchronized(FeaturePhonesComponentHolder::class) {
                if (component == null) {
                    component = PhonesComponent.get(dependencies)
                }
            }
        }
    }

    override fun get(): FeaturePhonesApi = requireNotNull(component) {
        "Phones component wasn't initialized"
    }

    override fun reset() {
        Log.d("MyLogs", "Phones component cleared")
        component = null
    }
}