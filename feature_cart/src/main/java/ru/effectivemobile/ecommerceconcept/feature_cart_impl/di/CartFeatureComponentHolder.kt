package ru.effectivemobile.ecommerceconcept.feature_cart_impl.di

import android.util.Log
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureApi
import ru.effectivemobile.ecommerceconcept.feature_cart_api.CartFeatureDependencies
import ru.effectivemobile.ecommerceconcept.feature_cart_impl.TAG
import ru.effectivemobile.ecommerceconcept.module_injector.ComponentHolder

/**
 * @see ComponentHolder
 */
object CartFeatureComponentHolder : ComponentHolder<CartFeatureApi, CartFeatureDependencies> {
    private var component: CartComponent? = null
    internal val featureComponent: CartComponent
        get() = requireNotNull(component) {
            "In field: CartFeatureComponent was not initialized"
        }

    override fun init(dependencies: CartFeatureDependencies) {
        if (component == null) {
            synchronized(CartFeatureComponentHolder::class) {
                if (component == null) {
                    Log.d(TAG,"Cart Feature component created")
                    component = CartComponent.get(dependencies)
                }
            }
        }
    }

    override fun get(): CartFeatureApi {
        return requireNotNull(component) {
            "CartFeatureApi was not initialized"
        }
    }

    override fun reset() {
        Log.d(TAG, "Cart Feature Holder destroyed")
        component = null
    }
}